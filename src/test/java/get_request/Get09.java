package get_request;

import base_url.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get09 extends RestfulBaseUrl {

    /*
    Given
        https://restful-booker.herokuapp.com/booking/91
    When
        I send GET Request to the url
    Then
        Response body should be like that;
        {
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}

 */

    @Test
    public void get09() {

        //set the url
        spec.pathParams("first", "booking", "second", 91);

        //set the expected data;
        /*
            "firstname": "Sally",
                "lastname": "Brown",
                "totalprice": 111,
                "depositpaid": true,
                "bookingdates": {
            "checkin": "2013-02-23",
                    "checkout": "2014-10-23"
        },
        "additionalneeds": "Breakfast"

    }
         */

        //en içtekı mapi oluşturalım(bookingdates key ine karsılık map bir value
        Map<String,String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin","2013-02-23");
        bookingdatesMap.put("checkout","2014-10-23");


        Map<String,Object> expectedData = new HashMap<>();

        expectedData.put("firstname","Sally");
        expectedData.put("lastname","Brown");
        expectedData.put("totalprice",111);
        expectedData.put("depositpaid",true);
        expectedData.put("bookingdates",bookingdatesMap);
        expectedData.put("additionalneeds","Breakfast");
        System.out.println(expectedData);
        System.out.println("***"+expectedData.get("bookingdates"));

//Send The Request and Get The Response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

//Do Assertion
        Map<String,Object> actualData = response.as(HashMap.class);
        System.out.println("actualData booking = " + actualData.get("bookingdates"));


        assertEquals("Sally",actualData.get("firstname"));
        assertEquals(expectedData.get("lastname"),actualData.get("lastname"));
        assertEquals(expectedData.get("totalprice"),actualData.get("totalprice"));
        assertEquals(expectedData.get("depositpaid"),actualData.get("depositpaid"));
        assertEquals("2013-02-23", ((Map)(actualData.get("bookingdates"))).get("checkin"));//Key-Value ikilileri String-Object şeklinde olduğundan, Bookingdata value kısmını casting ile Map yaptık.
        assertEquals(bookingdatesMap.get("checkout"), ((Map)(actualData.get("bookingdates"))).get("checkout"));

        /*
        Map im keyler String,valuelar Objeden oluşuyor,
        Obje classında get() metodu olmadıgı için actualData kısmında Objeyı Map e casting yaparak get()
        metodunu kullanmış oldum
         */




    }
}