import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.Is.is;

public class GetDataTests {

    @Test
    public void testStatusCode() {
        given().
                get("http://jsonplaceholder.typicode.com/posts/3").
                then().statusCode(200);
    }

    /*
    Verify code and print complete response
     */
    @Test
    public void testLogging() {
        given().
                get("http://services.groupkt.com/country/get/iso2code/in").then().statusCode(200).log().all();
    }

    /*
    Verifying multiple content
     */
    @Test
    public void testHasItem() {
        given().
                get("http://services.groupkt.com/country/get/all").then().
                body("RestResponse.result.name", hasItems("Argentina", "Australia", "Italy"));
    }

    /*
    parameters and headers can be set
     */
    @Test
    public void testParametersAndHeaders() {
        given().
                param("key1", "value1").
                header("headA", "valueA").
                when().
                get("http://services.groupkt.com/country/get/iso2code/gb").
                then().statusCode(200).log().all();
    }

    @Test
    public void testAnd() {
        given().param("key1", "value1").and().header("headA", "valueA").
                when().
                get("http://services.groupkt.com/country/get/iso2code/gb").
                then().
                statusCode(200).log().all();
    }

    @Test
    public void testEqualToFunction() {
        given().
                when().
                get("https://samples.openweathermap.org/data/2.5/weather?q=London,uk&appid=b6907d289e10d714a6e88b30761fae22").
                then().
                body("name", equalTo("London"));
    }

    @Test
    public void testOneParameter() {
        given().
                get("https://jsonplaceholder.typicode.com/posts/2").
                then().
                body("title", equalTo("qui est esse")).log().all();
    }

    @Test
    public void testParameters() {
        given().
                get("https://jsonplaceholder.typicode.com/posts/2").
                then().
                body("title", equalTo("qui est esse")).
                body("id", equalTo(2)).
                body("body", equalTo("est rerum tempore vitae" + "\n" +
                        "sequi sint nihil reprehenderit dolor beatae ea dolores neque" + "\n" +
                        "fugiat blanditiis voluptate porro vel nihil molestiae ut reiciendis"
                        + "\n" + "qui aperiam non debitis possimus qui neque nisi nulla")).
                log().all();
    }

    @Test
    public void testWithoutRoot() {
        given().
                get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
                then().
                body("sys.country",is("JP"));
    }

    @Test
    public void testWithoutRoot1() {
        given().
                get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
                then().
                body("sys.sunset",equalTo(1485763863));
    }

    @Test
    public void testWithoutRoot2() {
        given().
                get("https://samples.openweathermap.org/data/2.5/weather?lat=35&lon=139&appid=b6907d289e10d714a6e88b30761fae22").
                then().
                body("weather.id",contains(800));
    }

}
