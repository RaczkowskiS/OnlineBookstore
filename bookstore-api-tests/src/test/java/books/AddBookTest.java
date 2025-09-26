package books;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static rest.util.ResponseMapper.getResponseBody;

import java.time.Instant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.JsonNode;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import okhttp3.Response;
import rest.model.PostBookRequest;

@Epic("Bookstore API")
@Feature("Books")
@Tag("Regression")
public class AddBookTest extends BaseTest {
	
	@Story("POST /Books/{id} adds book to list")
	@Test
	@DisplayName("POST /Books/{id} -> Adds new book")
    public void postBooksId_addsBook() throws Exception
    {
		var request = new PostBookRequest()
			.setId(201)
			.setTitle("Test title")
			.setDescription("Test description")
			.setPageCount(123)
			.setExcerpt("Test excerpt")
			.setPublishDate(Instant.now());
		
		try (Response response = onlineBookstoreClient.addBook(request)) {
		  Allure.step("Assert HTTP 200");
	      assertEquals(200, response.code(), "HTTP status should be 200");
	      
	      Allure.step("Validate response");
	      var body = getResponseBody(response);
	      validateResponse(request, body);
		}
		
		try (Response response = onlineBookstoreClient.getBook(request.getId())) {
		  Allure.step("Check if book was added");
	      assertEquals(200, response.code(), "HTTP status should be 200");
	      
	      Allure.step("Validate book");
	      var body = getResponseBody(response);
	      validateResponse(request, body);
	    }
    }
	
	@Story("POST /Books/{id} return 400 if id already exists")
	@Test
	@DisplayName("POST /Books/{id} -> 400 if id already exists")
    public void postBooksId_return400ForExistingId() throws Exception
    {
		var request = new PostBookRequest()
			.setId(1)
			.setTitle("Test title")
			.setDescription("Test description")
			.setPageCount(123)
			.setExcerpt("Test excerpt")
			.setPublishDate(Instant.now());
		
		try (Response response = onlineBookstoreClient.addBook(request)) {
		  Allure.step("Assert HTTP 400");
	      assertEquals(400, response.code(), "HTTP status should be 400");
	    }
    }
	
	@Story("POST /Books/{id} return 400 if body is incorrect")
	@Test
	@DisplayName("POST /Books/{id} -> 400 if body is incorrect")
    public void postBooksId_return400ForIncorrectBody() throws Exception
    {		
		var request = "{\"book\": \"test\"}";
		
		try (Response response = onlineBookstoreClient.addBook(request)) {
		  Allure.step("Assert HTTP 400");
	      assertEquals(400, response.code(), "HTTP status should be 400");
	    }
    }
	
	private void validateResponse(PostBookRequest request, JsonNode response) {
		assertEquals(request.getId(), response.get("id").asInt());
		assertEquals(request.getTitle(), response.get("title").asText());
		assertEquals(request.getDescription(), response.get("description").asText());
		assertEquals(request.getPageCount(), response.get("pageCount").asInt());
		assertEquals(request.getExcerpt(), response.get("excerpt").asText());
		assertEquals(request.getPublishDate(), Instant.parse(response.get("publishDate").asText()));		
	} 
}
