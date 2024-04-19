import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class PetStoreAPITests extends TestBase{

    @Test(priority = 1)
    public void testCreatePet() {
        Map<String, Object> petData = new HashMap<>();
        petData.put("id", 12345);
        petData.put("name", "Fluffy");
        petData.put("status", "available");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petData)
                .post(BASE_URL+"/pet");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 12345);
        Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test(priority = 2)
    public void testGetPetById() {
        int petId = 12345;

        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .get("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), petId);
        Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy");
        Assert.assertEquals(response.jsonPath().getString("status"), "available");
    }

    @Test(priority = 3)
    public void testUpdatePet() {
        Map<String, Object> petData = new HashMap<>();
        petData.put("id", 12345);
        petData.put("name", "Fluffy the Great");
        petData.put("status", "pending");

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(petData)
                .put("/pet");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.jsonPath().getInt("id"), 12345);
        Assert.assertEquals(response.jsonPath().getString("name"), "Fluffy the Great");
        Assert.assertEquals(response.jsonPath().getString("status"), "pending");
    }

    @Test(priority = 4)
    public void testDeletePet() {
        int petId = 12345;

        Response response = RestAssured.given()
                .pathParam("petId", petId)
                .delete("/pet/{petId}");

        Assert.assertEquals(response.getStatusCode(), 200);
    }
}