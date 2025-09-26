package rest.util;

import java.time.Instant;

public class BodyHelper {
	private String body;
	
	public BodyHelper()
	{
		body = "";
	}
	
	public BodyHelper addBodyFieldMiddle(String name, String value)
	{
		body += "\"" + name + "\": \"" + value + "\",";
		return this;
	}
	
	public BodyHelper addBodyFieldMiddle(String name, int value)
	{
		body += "\"" + name + "\": " + value + ",";
		return this;
	}
	
	public BodyHelper addBodyFieldMiddle(String name, Instant value)
	{
		body += "\"" + name + "\": \"" + value + "\",";
		return this;
	}
	
	public BodyHelper addBodyFieldLast(String name, String value)
	{
		body += "\"" + name + "\": \"" + value + "\"";
		return this;
	}
	
	public BodyHelper addBodyFieldLast(String name, int value)
	{
		body += "\"" + name + "\": " + value + "";
		return this;
	}
	
	public BodyHelper addBodyFieldLast(String name, Instant value)
	{
		body += "\"" + name + "\": \"" + value + "\"";
		return this;
	}
	
	public BodyHelper addBodyPrefix()
	{
		body = "{" + body;
		return this;
	}
	
	public BodyHelper addBodySuffix()
	{
		body += "}";
		return this;
	}
	
	public String getBody()
	{
		return body;
	}
}
