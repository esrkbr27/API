package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GoRestBaseUrl {

    /*
   Given
       https://gorest.co.in/public/v1/users/2986
   When
       User send GET Request to the URL
   Then
       Status Code should be 200
   And
       Response body should be like
 {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Navin Talwar",
        "email": "navin_talwar@mclaughlin.name",
        "gender": "male",
        "status": "inactive"
    }
}
*/

    @Test
    public void get10() {


        spec.pathParams("first","users","second",2986);

        GoRestTestData obj = new GoRestTestData();
        Map<String,String> dataKeyMap = obj.dataKeyMap("Kanaka Jain","kanaka_jain@stark.net","male","active");
        Map<String,Object> expectedData = obj.expectedDataMethod(null, dataKeyMap);
  //      System.out.println(expectedData);

        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualData = response.as(HashMap.class);
  //      System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("meta"),actualData.get("meta"));
        assertEquals(dataKeyMap.get("name"), ((Map)actualData.get("data")).get("name"));
        assertEquals(dataKeyMap.get("email"), ((Map)actualData.get("data")).get("email"));
        assertEquals(dataKeyMap.get("gender"), ((Map)actualData.get("data")).get("gender"));
        assertEquals(dataKeyMap.get("status"), ((Map)actualData.get("data")).get("status"));
        assertEquals(200,response.statusCode());


        //Kısa yol;
        JsonPath json=response.jsonPath();
        Map<String,Object> datamap=  json.getMap("data");
        Map<String,Object> metamap= json.getMap("meta");


      System.out.println("datamap: " + datamap);
      System.out.println("metamap: " + metamap);
      System.out.println("datamapvalues = " + datamap.get("name"));
      System.out.println("metamap = " + metamap);


       assertEquals(dataKeyMap.get("name"), datamap.get("name"));
       assertEquals(dataKeyMap.get("email"), datamap.get("email"));
       assertEquals(dataKeyMap.get("gender"),datamap.get("gender"));


      // assertEquals(null, jsonPath.getString("meta"));
      // assertEquals("Kanaka Jain", jsonPath.getString("data.name"));
      // assertEquals("kanaka_jain@stark.net", jsonPath.getString("data.email"));
      // assertEquals("male", jsonPath.getString("data.gender"));
      // assertEquals("active", jsonPath.getString("data.status"));
      // assertEquals(response.statusCode(), 200);

    }
}