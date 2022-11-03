package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Get04 extends JsonplaceholderBaseUrl {

/*
    Given
        https://jsonplaceholder.typicode.com/todos
    When
   I send a GET request to the Url
 And
     Accept type is "application/json" (requestimin JSON formatta gittiğini test et )
 Then
     HTTP Status Code should be 200
 And
     Response format should be "application/json"
 And
     There should be 200 todos (200 tane id oldugunu)
 And
     "quis eius est sint explicabo" should be one of the todos title
     (bodydekı en az bir title burdakı ifadeye esit mi)
 And
     2, 7, and 9 should be among the userIds
     (userId ler arasında 2 , 7, 9 olmalı
 */

    @Test
    public void get01() {
        //1. set the url
       spec.pathParam("first","todos");

       //2. set the expected data (put post patch yok)

       //3. send the request and get the response
        //when den sonra and var accept (kabul edilebilir datanın contentType da JSON olmalı
        Response response =given().spec(spec).when().accept(ContentType.JSON).get("/{first}");

        response.prettyPrint();

        //4.Do Assertion (doğrulama yapmak)
        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("id",hasSize(200), "title",hasItem("quis eius est sint explicabo"),
                        "userId",hasItems(2,7,9));


         //Hamcrest Metotları;

        /*
        -hasSize();          - hasItem();
        -hasItems();
         */

        //Java kodlarını Json data tipine cevirmeye serialization
        //Response dan gelen json formatındakı datayı,java kodlarına cevirmeye de-serialization denir.

    }
}
