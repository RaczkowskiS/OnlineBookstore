package rest.client;

import okhttp3.Response;

public class OnlineBookstoreClient {

	private static final String clientUrl = "https://fakerestapi.azurewebsites.net/api/v1";
	private final RestAdapter http;
	
	public OnlineBookstoreClient() {
		http = new RestAdapter(clientUrl);
	}

	public Response getAllBooks() {
		return http.get("/Books");
	}
}
