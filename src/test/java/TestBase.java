import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class TestBase {
    protected static final String BASE_URL = "https://petstore.swagger.io/v2";

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }
}
