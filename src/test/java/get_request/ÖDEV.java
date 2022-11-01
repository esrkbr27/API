package get_request;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class ÖDEV {
    /*
 Given
     https://automationexercise.com/api/productsList
 When
     User sends a GET Request to the url
 Then
     HTTP Status Code should be 200
 And
     Content Type should be "text/html; charset=utf-8"
 And
     Status Line should be HTTP/1.1 200 OK
 And
      There must be 12 Women, 9 Men, 13 Kids usertype in products
   */

    @Test
    public void ödev() {

        String url="https://automationexercise.com/api/productsList";

        Response response=given().when().get(url);
        //response.prettyPrint();

        response.then().assertThat().statusCode(200);
        response.then().assertThat().contentType("text/html; charset=utf-8");
        response.then().assertThat().statusLine("HTTP/1.1 200 OK");

        JsonPath json=response.jsonPath();

      //  json.prettyPrint();

       List<String> usertypes=json.getList("products.category.usertype.usertype");
        System.out.println("usertypes = " + usertypes.toString());

        int womencount=0;
        int mencount=0;
        int kidscount=0;

        for (String w : usertypes) {
            if(w.equals("Women")){
                womencount++;
            } else if (w.equals("Men")){
                mencount++;
            }
            else if (w.equals("Kids")){
                kidscount++;
            }
        }
        System.out.println("womentotal:"+womencount);
        System.out.println("mencount:"+ mencount);
        System.out.println("kidscount:"+kidscount);

        assertEquals(12,womencount);
        assertEquals(9,mencount);
        assertEquals(13,kidscount);

    }
}
