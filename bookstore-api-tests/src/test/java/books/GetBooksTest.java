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
public class GetBooksTest extends BaseTest {
	
	@Story("GET /Books returns list of books")
	@Test
	@DisplayName("GET /Books -> All Books")
    public void getAllBooks_returnsBookList() throws Exception
    {
		try (Response response = onlineBookstoreClient.getAllBooks()) {
		  Allure.step("Assert HTTP 200");
	      assertEquals(200, response.code(), "HTTP status should be 200");
	      
	      Allure.step("Assert size of book list");
	      var body = getResponseBody(response);
	      assertTrue(body.isArray(), "Response should be a list");
	      assertTrue(body.size() > 0, "Book list shouldn't be empty");
	      
	      Allure.step("Validate book list structure");
	      for (int bookIndex = 0; bookIndex<body.size(); bookIndex++) {
	    	  checkBookStructure(body.get(bookIndex));
	      }
	    }
    }
	
	private void checkBookStructure(JsonNode book) {
		assertDoesNotThrow(() -> Integer.parseInt(book.get("id").asText()), "id isn't a number");
		assertNotNull("title field doesn't exist", book.get("title").asText());
		assertNotNull("description field doesn't exist", book.get("description").asText());
		assertDoesNotThrow(() -> book.get("pageCount").asInt(), "id isn't a number");
		assertNotNull("excerpt field doesn't exist", book.get("excerpt").asText());
		assertDoesNotThrow(() -> Instant.parse(book.get("publishDate").asText()));
	}
}
