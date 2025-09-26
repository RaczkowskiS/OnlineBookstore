package books;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import base.BaseTest;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import okhttp3.Response;

@Epic("Bookstore API")
@Feature("Books")
@Tag("Regression")
public class DeleteBookTest extends BaseTest {

	@Story("DELETE /Books/{id} deletes book")
	@Test
	@DisplayName("DELETE /Books/{id} -> Deletes book")
    public void deleteBooksId_deletesBook() throws Exception
    {	
		try (Response response = onlineBookstoreClient.deleteBook(1)) {
		  Allure.step("Assert HTTP 200");
	      assertEquals(200, response.code(), "HTTP status should be 200");
		}
		
		try (Response response = onlineBookstoreClient.getBook(1)) {
		  Allure.step("Check if book was deleted");
	      assertEquals(404, response.code(), "HTTP status should be 404");
	    }
    }
	
	@Story("DELETE /Books/{id} returns 404 for not existing id")
	@Test
	@DisplayName("DELETE /Books/{id} -> 404 for not existing id")
    public void deleteBooksId_returns404ForNotExistingBook() throws Exception
    {		
		try (Response response = onlineBookstoreClient.getBook(999)) {
		  Allure.step("Check if book was deleted");
	      assertEquals(404, response.code(), "HTTP status should be 404");
	    }
    }
}
