package books;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
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

@Epic("Bookstore API")
@Feature("Books")
@Tag("Regression")
public class GetBookTest extends BaseTest {

	@Story("GET /Books/{id} returns book with id")
	@Test
	@DisplayName("GET /Books/{id} -> Book with id")
    public void getBooksId_returnsExistingBook() throws Exception
    {
		try (Response response = onlineBookstoreClient.getBook(1)) {
		  Allure.step("Assert HTTP 200");
	      assertEquals(200, response.code(), "HTTP status should be 200");
	      
	      Allure.step("Validate book list structure");
	      var body = getResponseBody(response);
	      validateBook(body);
	    }
    }
	
	@Story("GET /Books/{id} returns 404 for not existing id")
	@Test
	@DisplayName("GET /Books/{id} -> 404 for not existing book")
    public void getBooksId_returns404ForNotExistingBook() throws Exception
    {
		try (Response response = onlineBookstoreClient.getBook(999)) {
		  Allure.step("Assert HTTP 404");
	      assertEquals(404, response.code(), "HTTP status should be 404");
	      
	      Allure.step("Validate book list structure");
	      var body = getResponseBody(response);
	      validateNotFoundMessage(body);
	    }
    }
	
	private void validateBook(JsonNode book) {
		assertEquals(1, book.get("id").asInt());
		assertEquals("Book 1", book.get("title").asText());
		assertTrue(book.get("description").asText().contains("Lorem lorem lorem."));
		assertEquals(100, book.get("pageCount").asInt());
		assertTrue(book.get("excerpt").asText().contains("Lorem lorem lorem."));
		assertDoesNotThrow(() -> Instant.parse(book.get("publishDate").asText()));	
	}
	
	private void validateNotFoundMessage(JsonNode response) {
		assertNotNull("type field doesn't exist", response.get("type").asText());
		assertEquals("Not Found", response.get("title").asText());
		assertEquals(404, response.get("status").asInt());
		assertNotNull("traceId field doesn't exist", response.get("traceId").asText());
	}
}
