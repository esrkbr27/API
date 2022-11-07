package get_request;

import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import base_url.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import utils.ObjectMapperUtils;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {


    /*
        Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
           {
                "firstname": "Oliver",
                "lastname": "Smith",
                "totalprice": 100,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2022-07-18",
                    "checkout": "2022-07-19"
                },
                "additionalneeds": "Breakfast"
            }
     */

    @Test
    public void get15() {
        //set the url
        spec.pathParams("first","booking","second",22);

        //set the expectedData

        BookingDatesPojo bookingDatesPojo= new BookingDatesPojo("2018-01-01","2022-07-19");

        BookingPojo expectedData= new BookingPojo("Morante Briones","Morante Briones",111,true,bookingDatesPojo,"Breakfast");

        //send the requets

       Response response= given().spec(spec).when().get("/{first}/{second}");
       response.prettyPrint();

        //Do Assertion
        BookingPojo actualData=  ObjectMapperUtils.convertJsonToJava(response.asString(), BookingPojo.class);

        //Hard Assertion
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getFirstname(), actualData.getLastname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());

        //Soft Assertion

       //i.Adım SoftAssert objesı oluştur

       SoftAssert softAssert = new SoftAssert();

       //ii.Adım Assertion yap
       softAssert.assertEquals(actualData.getFirstname(),expectedData.getFirstname(),"First name uyusmadi");
       softAssert.assertEquals(actualData.getLastname(),expectedData.getLastname(),"Lastname uyusmadi");
       softAssert.assertEquals(actualData.getTotalprice(),expectedData.getTotalprice(),"Totalprice uyusmadi");
       softAssert.assertEquals(actualData.getDepositpaid(),expectedData.getDepositpaid(),"Depositpaid uyusmadi");
       softAssert.assertEquals(actualData.getAdditionalneeds(),expectedData.getAdditionalneeds(),"Aditionalneed uyusmadi");

     softAssert.assertEquals(actualData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin(),"Check ın hatalı");
     softAssert.assertEquals(actualData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout(),"Check out hatalı");

      //iii.Adım Assert All metodunu kullan

        softAssert.assertAll();
    }
}
