package books;

import static org.junit.Assert.assertNotNull;
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
import rest.model.PutBookRequest;

@Epic("Bookstore API")
@Feature("Books")
@Tag("Regression")
public class EditBookTest extends BaseTest {

	@Story("PUT /Books/{id} edits book")
	@Test
	@DisplayName("PUT /Books/{id} -> Edits book")
    public void putBooksId_editsBook() throws Exception
    {
		var request = new PutBookRequest()
			.setId(1)
			.setTitle("Test title")
			.setDescription("Test description")
			.setPageCount(123)
			.setExcerpt("Test excerpt")
			.setPublishDate(Instant.now());
		
		try (Response response = onlineBookstoreClient.editBook(request.getId(), request)) {
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
	
	@Story("PUT /Books/{id} return 400 if body is incorrect")
	@Test
	@DisplayName("PUT /Books/{id} -> 400 if body is incorrect")
    public void putBooksId_return400ForIncorrectBody() throws Exception
    {		
		var request = "{\"book\": \"test\"}";
		
		try (Response response = onlineBookstoreClient.editBook(1, request)) {
		  Allure.step("Assert HTTP 400");
	      assertEquals(400, response.code(), "HTTP status should be 400");
	    }
    }
	
	@Story("PUT /Books/{id} return 404 for not existing book\"")
	@Test
	@DisplayName("PUT /Books/{id} -> 404 for not existing book\"")
    public void putBooksId_404ForNotExistingBook() throws Exception
    {
		var request = new PutBookRequest()
			.setId(999)
			.setTitle("Test title")
			.setDescription("Test description")
			.setPageCount(123)
			.setExcerpt("Test excerpt")
			.setPublishDate(Instant.now());
		
		try (Response response = onlineBookstoreClient.editBook(request.getId(), request)) {
		  Allure.step("Assert HTTP 404");
	      assertEquals(404, response.code(), "HTTP status should be 404");
	      
	      Allure.step("Validate book list structure");
	      var body = getResponseBody(response);
	      validateNotFoundMessage(body);
		}
    }
	
	private void validateResponse(PutBookRequest request, JsonNode response) {
		assertEquals(request.getId(), response.get("id").asInt());
		assertEquals(request.getTitle(), response.get("title").asText());
		assertEquals(request.getDescription(), response.get("description").asText());
		assertEquals(request.getPageCount(), response.get("pageCount").asInt());
		assertEquals(request.getExcerpt(), response.get("excerpt").asText());
		assertEquals(request.getPublishDate(), Instant.parse(response.get("publishDate").asText()));		
	}
	
	private void validateNotFoundMessage(JsonNode response) {
		assertNotNull("type field doesn't exist", response.get("type"));
		assertEquals("Not Found", response.get("title").asText());
		assertEquals(404, response.get("status").asInt());
		assertNotNull("traceId field doesn't exist", response.get("traceId"));
	}
}
