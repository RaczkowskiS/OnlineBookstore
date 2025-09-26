pipeline {
    agent any
    
    tools {
        jdk   'JDK17'
        maven 'Maven_3.9'
    }
    
    stages {
        stage('Download repository') {
            steps {
                git url: 'https://github.com/RaczkowskiS/OnlineBookstore.git',
                    branch: 'main',
                    credentialsId: ''
            }
        }
        
        stage('Build project') {
            steps {
                dir('bookstore-api-tests') {
                    script {
                        def mvn = isUnix() ? 'mvn' : 'mvn.cmd'
                        def cmd = "${mvn} -B -q clean package -DskipTests"
                        if (isUnix()) sh cmd else bat cmd
                    }
                }
            }
        }
        
        stage('Run Health Checks') {
            steps {
                dir('bookstore-api-tests') {
                    script {
                        def mvn = isUnix() ? 'mvn' : 'mvn.cmd'
                        def cmd = """
                          ${mvn} -B test -P HealthCheck \
                            -DbaseUrl=${params.BASE_URL} \
                            -Dallure.results.directory=target/allure-results
                        """.trim()
                        if (isUnix()) sh cmd else bat cmd
                    }
                }
            }
        }
        
        stage('Run Regression') {
            steps {
                dir('bookstore-api-tests') {
                    script {
                        def mvn = isUnix() ? 'mvn' : 'mvn.cmd'
                        def cmd = """
                          ${mvn} -B test -P Regression \
                            -DbaseUrl=${params.BASE_URL} \
                            -Dallure.results.directory=target/allure-results
                        """.trim()
                        if (isUnix()) sh cmd else bat cmd
                    }
                }
            }
            post {
                always {
                  junit allowEmptyResults: true, testResults: '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Generate Allure HTML') {
            steps {
                dir('bookstore-api-tests') {
                  bat 'mvn -B -q allure:report'
                }
            }
        }
        
        stage('Publish HTML report') {
            steps {
                publishHTML(target: [
                    reportName: 'Allure Report',
                    reportDir: 'bookstore-api-tests/target/site/allure-maven-plugin',
                    reportFiles: 'index.html',
                    keepAll: true,
                    alwaysLinkToLastBuild: true,
                    allowMissing: true
                ])
            }
        }
    }
}
