package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class Get05 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Ali" and lastname is "Cengiz"
   /Datanda  ismi Ali soy ismi Cengiz olan biri oldugunu doğrula
 */

    @Test
    public void get01() {
        //end pointte parametre oldugu için önce patparam yaptık
        //1. set the url
        //https://restful-booker.herokuapp.com/booking?firstname=Johnny&lastname=Dear
        //? query oldugunu gösteriyor,? den sonrakı hersey artık query parametreleri
        spec.pathParam("first","booking").queryParams("firstname","Ali","lastname",
                "Cengiz");

        //2. set the expected data (casede yok)

        //3.sends get request to the URL
         //query paramları asagıda eklemesem de yukarıdan spec ile gelmiş oluyor
        Response response =given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //4.Do Assertion (doğrulama yapmak)
        //gönderdiğim requeste karsılık bana respose da sadece booking id ler geliyor(firstname,lastname gelmiyor)
      assertEquals(200,response.getStatusCode());
      assertTrue(response.asString().contains("bookingid"));


    }
}
