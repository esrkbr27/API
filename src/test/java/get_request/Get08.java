package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    //

    /*
    Given
       https://jsonplaceholder.typicode.com/todos/2
   When
       I send GET Request to the URL
   Then
       Status code is 200
       And "completed" is false
       And "userId" is 1
       And "title" is "quis ut nam facilis et officia qui"
       And header "Via" is "1.1 vegur"
       And header "Server" is "cloudflare"
       {
           "userId": 1,
           "id": 2,
           "title": "quis ut nam facilis et officia qui",
           "completed": false
       }
*/

    @Test
    public void get08() {
         //set the url
        spec.pathParams("first","todos","second",2);

        //set the expected data (payload)

        //test case de verien metin ile response dakı aynı mı değil mi doğrulama yapmam lazım,
        //Json formatına java da en yakın Map oldugu için
        //karşılaştırmak için bir map oluşturuyorum
        //keylerin hepsi string, valuelar farklı data tıpleri oldugu için Object yazdık
        Map <String,Object> expected = new HashMap<String,Object>(Map.of("userId",1,"id",2,
                "title","quis ut nam facilis et officia qui","completed",false));
       // expected.put("userId",1);
       // expected.put("id",2);
       // expected.put("title","quis ut nam facilis et officia qui");
       // expected.put("completed",false);

        System.out.println("Expected data:"+ expected);
        //Hash Map hızlıdır fakat sıralı değildir, bu nedenle konsolda sıralama farklı olabilir

        //send request

        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion

        Map actualdata=response.as(HashMap.class); //De-serialization
        System.out.println("Actual data: " + actualdata);

      //  Map <String,Object> expected = new HashMap<String,Object>(Map.of("userId",1,"id",2,
       //         "title","quis ut nam facilis et officia qui","completed",false));

        //tek tek expected actuala eşit mi onu kontorl edecegız
        assertEquals(expected.get("userId"), actualdata.get("userId"));
        assertEquals(expected.get("id"), actualdata.get("id"));
        assertEquals(expected.get("title"), actualdata.get("title"));
        assertEquals(expected.get("completed"), actualdata.get("completed"));
        assertEquals("1.1 vegur",response.header("Via"));
        assertEquals("cloudflare",response.header("Server"));
        assertEquals(200,response.getStatusCode());

    }


    //2. dinamik yöntem

    @Test
    public void get08b(){

//Set the Url
        spec.pathParams("first","todos","second",2);

//Set The Expected Data ==> Payload
        JsonPlaceHolderTestData objJsonPlcHldr = new JsonPlaceHolderTestData();

        Map<String,Object> expectedData = objJsonPlcHldr.expectedDataMethod(1,"quis ut nam facilis et officia qui",false);
        System.out.println(expectedData);


//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
//Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);//De-Serialization
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.get("userId"),actualData.get("userId"));
        assertEquals(expectedData.get("title"),actualData.get("title"));
        assertEquals(expectedData.get("completed"),actualData.get("completed"));
        assertEquals("1.1 vegur", response.header("Via"));
        assertEquals("cloudflare", response.header("Server"));
        assertEquals(200, response.statusCode());

    }
}
