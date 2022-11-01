package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.testng.AssertJUnit.*;

public class Get06 extends RestfulBaseUrl {

    /*
       Given
           https://restful-booker.herokuapp.com/booking/22
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
        {
          "firstname": "Howard",
       "lastname": "Colque",
       "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
       "checkin": "2018-01-01",
       "checkout": "2019-01-01"
   },
    "additionalneeds": "Breakfast"
}
    */

    @Test
    public void get01() {

      //  1.set the url
        spec.pathParams("first","booking","second",2325);

     //2.set the expected data (put,post,patch)

     //3.send the request and get the response
     Response response =given().spec(spec).when().get("/{first}/{second}");
     response.prettyPrint();

     //4.Do Assertion (doğrulama yapmak)
        /*   Response body should be like;
        {
          "firstname": "Bradley",
       "lastname": "Pearson",
       "totalprice": 132,
          "depositpaid": false,
          "bookingdates": {    //outer JSON code
       "checkin": "2022-10-27",
       "checkout": "2022-11-07"  //inner JSON code
   },
    "additionalneeds": "None"
}
    */
        // 1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2022-10-27"), //bookingdates de { farklı bir body baslıyor
                        "bookingdates.checkout",equalTo("2022-11-07"),
                        "additionalneeds",equalTo("None") );



     //2.Yol json path kullanımı

        JsonPath jsons=response.jsonPath();
        assertEquals("Bradley",jsons.get("firstname")) ;
        assertEquals("Pearson",jsons.get("lastname")) ;
        assertEquals(132,jsons.getInt("totalprice"));
        assertFalse(jsons.getBoolean("depositpaid"));
        assertEquals("2022-10-27",jsons.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",jsons.getString("bookingdates.checkout"));
        assertEquals("None",jsons.getString("additionalneeds"));

      //3.yol SoftAssert kullanımı
        /*
        SoftAssert classını kullanmak için 3 adım izlenir
        1.Obje oluşturmak
        */
        SoftAssert softAssert = new SoftAssert();

        //2.Dogrulama yapmak
        softAssert.assertEquals(jsons.getString("firstname"),"Bradley*","firstname hatalı");
        softAssert.assertEquals(jsons.getString("lastname"),"Pearson","lastname hatalı");
        softAssert.assertEquals(jsons.getInt("totalprice"),132, "totalprice hatali");
        softAssert.assertEquals(jsons.getBoolean("depositpaid"),"depositpaid hatalı");
        softAssert.assertEquals(jsons.getString("bookingdates.checkin"),"2022-10-27","checkin hatalı");
        softAssert.assertEquals(jsons.getString("bookingdates.checkout"),"2022-11-07","checkout hatalı");
        softAssert.assertEquals(jsons.getString("additionalneeds"),"None","additionalneeds hatalı");

        //3. softAssert.assertAll();
        softAssert.assertAll();


        //doğrulama ilemleri sonunda softAssert.assertAll() diyerek doğrulama işlemlerinin kontrol edilmesini saglıyoruz
        //Eger işlemlerin sonunda kullnamaz isek taleplerimiz hatalı dahı olsa testimiz pass olacaktır











                      }
}
