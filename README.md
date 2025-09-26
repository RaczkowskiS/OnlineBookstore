# Online Bookstore – REST API Test Automation Framework

## Requirements
- **Java 17+**

## Run tests
```bash
# All tests
mvn clean test

# HealthCheck suite
mvn clean test -P HealthCheck    # or: mvn clean test -Dgroups=HealthCheck

# Regression suite
mvn clean test -P Regression     # or: mvn clean test -Dgroups=Regression
```

## Generate Allure report
```bash
# Quick preview on a local server (opens browser)
mvn allure:serve

# Or generate static HTML:
mvn allure:report
# -> open: target/site/allure-maven-plugin/index.html
```
> After running tests, Allure results are written to `target/allure-results`.