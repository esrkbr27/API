package get_request;

import base_url.ReqresinBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get02b extends ReqresinBaseUrl {

      /*
   Given
       https://reqres.in/api/users/23
   When
       User send a GET Request to the url
   Then
       HTTP Status code should be 404
   And
       Status Line should be HTTP/1.1 404 Not Found
   And
       Server is "cloudflare"
   And
       Response body should be empty

*/

    @Test
    public void get02() {
        //1.set the url
        spec.pathParams("first","api","second","users","third",23);

        //2.set the expected data

        //3.send the request

        Response response =given().spec(spec).when().get("/{first}/{second}/{third}");
        response.prettyPrint();

        //4.Do Assertion (doğrulama yapmak)
        //1.yol
        response.then().assertThat().
                statusCode(404);

        //2.yol
        assertEquals(404,response.getStatusCode());
        assertEquals("HTTP/1.1 404 Not Found",response.getStatusLine());
        assertEquals("cloudflare",response.header("Server"));
       assertEquals("2",response.asString().replaceAll("\\s","").length());

    }
}