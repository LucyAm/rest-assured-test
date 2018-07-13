import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutDataTest {

    @Test
    public void putTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts/1";
        RequestSpecification request = given();

        JSONObject requestParameters = new JSONObject();
        requestParameters.put("title","foo");
        requestParameters.put("body", "bar");
        requestParameters.put("userId", 1);

        request.header("Content-Type", "application/json");
        request.body(requestParameters.toString());
        Response response = request.put();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);


    }
}
