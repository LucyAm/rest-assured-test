import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostDataTests {
    @Test
    public void postTest1() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RequestSpecification requestSpecification = given();

        JSONObject requestParameters = new JSONObject();
        requestParameters.put("title", "foo");
        requestParameters.put("body", "bar");
        requestParameters.put("userId", "1");

        requestSpecification.body(requestParameters.toString());
        Response response = requestSpecification.post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(statusCode, 201);

    }

    @Test
    public void postTest2() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/posts";
        RequestSpecification request = given();


        JSONObject requestParameters = new JSONObject();
        requestParameters.put("title","J Hope");
        requestParameters.put("body", "BTS");
        requestParameters.put("userId", 1);

        request.header("Content-Type", "application/json");
        request.body(requestParameters.toString());
        Response response = request.post();

        int statusCode = response.getStatusCode();
        System.out.println(response.getStatusCode());
        Assert.assertEquals(statusCode, 201);
    }

    /*
  Bad Request response code
   */
    @Test
    public void postTest3() {
        given().
                header("AppKey", "Key-Value").
                param("firstName", "first").
                param("lastName", "last").
                param("userEmail", "test@test.com").
                when().
                post("http://api.fonts.com/rest/json/Accounts").
                then().
                statusCode(400).log().all();

    }

}
