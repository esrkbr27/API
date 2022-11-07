package get_request;

import Pojos.GoRestDataPojo;
import Pojos.GoRestPojo;
import base_url.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {

    /*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */

    @Test
    public void get13() {

        //set the url
        spec.pathParams("first","users","second",2508);

        //set the expecteddata

        GoRestDataPojo gorestdata =new GoRestDataPojo(2508,"Sharmila Deshpande VM","deshpande_sharmila_vm@becker.name",
                                                      "female","active");

        GoRestPojo expectedData=new GoRestPojo(null,gorestdata);

        //send the request
          Response response= given().spec(spec).when().get("/{first}/{second}");

          //Do Assertion
            GoRestPojo actualData= response.as(GoRestPojo.class);
        System.out.println("actualData = " + actualData);

        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(gorestdata.getId(), actualData.getData().getId());
        assertEquals(gorestdata.getName(), actualData.getData().getName());
        assertEquals(gorestdata.getEmail(), actualData.getData().getEmail());
        assertEquals(gorestdata.getGender(), actualData.getData().getGender());
        assertEquals(gorestdata.getStatus(), actualData.getData().getStatus());
    }

    }

