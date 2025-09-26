package rest.client;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import rest.http.Headers;
import rest.http.MediaTypes;

public class RestAdapter {

	private OkHttpClient httpClient;
	private final String clientUrl;
	
	public RestAdapter(String clientUrl) {
		this.clientUrl = clientUrl;
	    this.httpClient = new OkHttpClient();
	}
	
	public Response delete(String requestPath)
	{
		var request = new Request.Builder()
            .url(clientUrl + requestPath)
            .delete()
            .addHeader(Headers.ACCEPT.value, MediaTypes.JSON)
            .build();
		
		try {
			var response = httpClient.newCall(request).execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Response get(String requestPath)
	{
		var request = new Request.Builder()
            .url(clientUrl + requestPath)
            .addHeader(Headers.ACCEPT.value, MediaTypes.JSON)
            .build();
		
		try {
			var response = httpClient.newCall(request).execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Response post(String requestPath, String requestBody)
	{
		var body = RequestBody.create(requestBody, MediaType.parse(MediaTypes.JSON));
		
		var request = new Request.Builder()
            .url(clientUrl + requestPath)
            .post(body)
            .addHeader(Headers.CONTENT_TYPE.value, MediaTypes.JSON)
            .addHeader(Headers.ACCEPT.value, MediaTypes.JSON)
            .build();
		
		try {
			var response = httpClient.newCall(request).execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Response put(String requestPath, String requestBody)
	{
		var body = RequestBody.create(requestBody, MediaType.parse(MediaTypes.JSON));
		
		var request = new Request.Builder()
            .url(clientUrl + requestPath)
            .put(body)
            .addHeader(Headers.CONTENT_TYPE.value, MediaTypes.JSON)
            .addHeader(Headers.ACCEPT.value, MediaTypes.JSON)
            .build();
		
		try {
			var response = httpClient.newCall(request).execute();
			return response;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
