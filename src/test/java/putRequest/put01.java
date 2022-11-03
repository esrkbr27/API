package putRequest;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.replaceFiltersWith;
import static org.junit.Assert.assertEquals;

public class put01 extends JsonplaceholderBaseUrl {

    /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */

    @Test
    public void put01() {

        //set the url
        spec.pathParams("first","todos","second",198);

        //set the expected data

        JsonPlaceHolderTestData obje= new JsonPlaceHolderTestData();
        Map<String,Object> expectedData =obje.expectedDataMethod(21,"Wash the dishes",false);

        //send the request get the response
        Response response =given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().put("/{first},/{second}");

      //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals(200,response.getStatusCode());

    }
}
