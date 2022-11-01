package get_request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01b {
    /*
   Given
       https://reqres.in/api/users/3
   When
       User sends a GET Request to the url
   Then
       HTTP Status Code should be 200
   And
       Content Type should be JSON
   And
       Status Line should be HTTP/1.1 200 OK
*/


    @Test
    public void get01() {

        //i.Adım; set the url
        String url="https://reqres.in/api/users/3";

        // ii) Set the expected data (beklenen datanın oluşturulması

        //iii)send the request
        Response response=given().when().get(url); //(get request) yaptık postman de url yazdık ve send dedik
        response.prettyPrint(); //responseu yazdırır

        //iv)Do Assertion (doğrulama yapmak)
        response.then().assertThat().  //assertthat(). yazmadan da doğrulama yapılıyor.
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

    }
}
