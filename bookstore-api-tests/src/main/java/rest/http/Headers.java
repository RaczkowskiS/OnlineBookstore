package rest.http;

public enum Headers {
	ACCEPT("Accept"),
    CONTENT_TYPE("Content-Type");
    
    public final String value;
	Headers(String v) { this.value = v; }
}
