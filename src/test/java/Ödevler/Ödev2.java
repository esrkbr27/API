package Ödevler;

import base_url.ReqresinBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Ödev2 extends ReqresinBaseUrl {

    //2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"
                                              }
*/

    @Test
    public void ödv2() {
        spec.pathParam("first","users");

        //set the expected data
        Map<String,String> expecteddata = new HashMap<String,String>(Map.of("name","morpheus","job","leader"));

        //send the request


    }
}
