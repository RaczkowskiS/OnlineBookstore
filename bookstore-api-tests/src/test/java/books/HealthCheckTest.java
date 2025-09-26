package books;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import base.BaseTest;
import okhttp3.Response;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Bookstore API")
@Feature("Books")
@Tag("HealthCheck")
public class HealthCheckTest extends BaseTest {

	@Story("GET /Books returns 200")
	@Test
	@DisplayName("GET /Books -> 200")
    public void getAllBooks_returns200() throws Exception
    {
		try (Response response = onlineBookstoreClient.getAllBooks()) {
		  Allure.step("Assert HTTP 200");
	      assertEquals(200, response.code(), "HTTP status should be 200");
	    }
    }
	
}
