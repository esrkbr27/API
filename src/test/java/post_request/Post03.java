package post_request;

import Pojos.JsonPlaceHolderPojo;
import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03 extends JsonplaceholderBaseUrl {

    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
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
    public void post03() {
        //set the url
        spec.pathParam("first","todos");

        //set the expected data (payload data oluşturacagız)
        JsonPlaceHolderPojo expectedData= new JsonPlaceHolderPojo(55,"Tidy your room",false);
        System.out.println("expecteddata:"+expectedData);

        //send the post request
        Response response= given().spec(spec).contentType(ContentType.JSON).body(expectedData).post("/{first}");
        response.prettyPrint();

        //Do Assertion
          JsonPlaceHolderPojo actualdata=response.as(JsonPlaceHolderPojo.class);
        System.out.println("actualdata: " + actualdata);

        //Assertion yaparken expectedDatayi içine attıgımız pojo classın objesi üzerinden keylerin valuelarını cagırıyorum
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.getUserId(),actualdata.getUserId());
        assertEquals(expectedData.getTitle(),actualdata.getTitle());
        assertEquals(expectedData.getCompleted(),actualdata.getCompleted());






    }
    }

