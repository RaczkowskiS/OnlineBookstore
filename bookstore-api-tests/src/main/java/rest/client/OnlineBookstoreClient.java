package rest.client;

import okhttp3.Response;
import rest.model.PostBookRequest;
import rest.model.PutBookRequest;

public class OnlineBookstoreClient {

	private static final String clientUrl = "https://fakerestapi.azurewebsites.net/api/v1";
	private final RestAdapter http;
	
	public OnlineBookstoreClient() {
		http = new RestAdapter(clientUrl);
	}
	
	public Response addBook(PostBookRequest request) {
		return http.post("/Books", request.GetRequestBody());
	}
	
	public Response addBook(String request) {
		return http.post("/Books", request);
	}
	
	public Response deleteBook(int id) {
		return http.delete("/Books/" + id);
	}
	
	public Response editBook(int id, PutBookRequest request) {
		return http.put("/Books/" + id, request.GetRequestBody());
	}
	
	public Response editBook(int id, String request) {
		return http.put("/Books/" + id, request);
	}

	public Response getAllBooks() {
		return http.get("/Books");
	}
	
	public Response getBook(int id) {
		return http.get("/Books/" + id);
	}
}
