package post_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {

     /*
    Given
      1) https://jsonplaceholder.typicode.com/todos

      2)body kısmı su sekılde olmalı{
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
                         }
   When
       I send POST Request to the Url
   Then
       Status code is 201
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/

    @Test
    public void post01() {

        //set the url
        spec.pathParam("first","todos");

        //set the expected data
        JsonPlaceHolderTestData obje= new JsonPlaceHolderTestData();
        Map<String,Object> expectedmap=obje.expectedDataMethod(55,"Tidy your room",false);

        //send the request
        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedmap).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);

        assertEquals(actualData.get("completed"),expectedmap.get("completed"));
        assertEquals(actualData.get("title"),expectedmap.get("title"));
        assertEquals(actualData.get("userId"),expectedmap.get("userId"));
        assertEquals(201,response.getStatusCode());

        //serialization da biz java kodunu yazdıktan sonra API onu Jsona cevirip post öyle yapıyor
        //de-serialization da gelen response Json formatında gelir as(HashMap.class) metodu ile mape ceviriyoruz


    }
}
