package post_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.RestfulTestData;

import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {

 /*
   Given
       1) https://restful-booker.herokuapp.com/booking
       2) expected data:{
            "firstname": "John",
            "lastname": "Doe",
            "totalprice": 11111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2021-09-09",
                "checkout": "2021-09-21"
             }
          }
   When
       I send POST Request to the Url
   Then
       Status code is 200
       And response body should be like {
                                           "bookingid": 5315,
                                           "booking": {
                                               "firstname": "John",
                                               "lastname": "Doe",
                                               "totalprice": 11111,
                                               "depositpaid": true,
                                               "bookingdates": {
                                                   "checkin": "2021-09-09",
                                                   "checkout": "2021-09-21"
                                               }
                                           }
                                        }
*/

    @Test
    public void post01() {

        //set the expected data

        spec.pathParam("first","booking");

        //set the expected data
        RestfulTestData obj1= new RestfulTestData();
        Map<String,String> bookingdatesmap= obj1.bookingdatesMethod("2021-09-09","2021-09-21");
        Map<String,Object> expectedData=obj1.expectedDataMethod("John","Doe",11111,
                true,bookingdatesmap);

        //send the request

        Response response=given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");

        //Do Assertion
        Map<String,Object> actualData=response.as(HashMap.class); //De serialization
        System.out.println(actualData);

        response.then().assertThat().statusCode(200);

        assertEquals(expectedData.get("firstname"),((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map)actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map)actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),((Map) actualData.get("booking")).get("depositpaid"));

        assertEquals(bookingdatesmap.get("checkin"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(bookingdatesmap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));

    }





}
