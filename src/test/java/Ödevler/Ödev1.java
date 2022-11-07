package Ödevler;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Ödev1 {

    //Alıştırmalar:

        //1:
 /*
        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
*/

    @Test
    public void get() {
        //set the url
        String url=" https://automationexercise.com/api/brandsList";

        // ii) Set the expected data (beklenen datanın oluşturulması

        //send the request
        Response response =given().when().get(url);

        //Do Assertion
        response.then().assertThat().statusCode(200).
                contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");

           JsonPath json= response.jsonPath();
        List<String> brands=   json.getList("brands.brand");
        System.out.println(brands);



    }









}
