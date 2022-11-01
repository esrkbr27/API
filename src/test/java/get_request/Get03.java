package get_request;

import base_url.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*  ;

public class Get03 extends JsonplaceholderBaseUrl {
 /*
      Given
          https://jsonplaceholder.typicode.com/todos/23
      When
          User send GET Request to the URL
      Then
          HTTP Status Code should be 200
And
    Response format should be "application/json" (response format =contentType)
And
    "title" is "et itaque necessitatibus maxime molestiae qui quas velit",
And
    "completed" is false
And
    "userId" is 2
   */

    @Test
    public void get01() {
        //set the url
        //base urle todos 23 kısmını asagıdakı seklıyle eklemiş olduk
        //{{Jsonbaseurl}}/todos/210
        spec.pathParams("first","todos","second","23");

        //Expected Data(Put,Patch,Post)

        //Send the request and get the response
        Response response= given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        // Do Assertion (doğrulama yapmak

        //1.yol Hard Assert

        //Testimin fail oldugu yerde kodların calışması durur,
        // bodylerı ayrı parantezlerde yazarsam (hard assert) olur.
        //fail oldugu yerde alttakı body metoduna gecemez.
        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed",equalTo(false)).
                body("userId",equalTo(2));

        //2.yol Sadece body içerisinde gecerli (SoftAssert) body tek parantez içerisnde yazıldı
        //bodynın içinde herhangı biri fail olursa diğerlerine de bakıyor.sonra calısmayı duruduruyor

        response.then().assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title",equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed",equalTo(false),"userId",equalTo(2));

    }
}
