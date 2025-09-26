package rest.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.Response;

public class ResponseMapper {

	private static ObjectMapper om = new ObjectMapper().findAndRegisterModules();
	
	public static JsonNode getResponseBody(Response response) throws IOException {
		String body = response.body().string();
		return om.readTree(body);
	}
}
