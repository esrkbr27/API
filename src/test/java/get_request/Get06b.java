package get_request;

import base_url.ReqresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;

public class Get06b extends ReqresinBaseUrl {

     /*
   Given
          https://reqres.in/api/unknown/
   When
        I send GET Request to the URL
   Then

        1)Status code is 200
        2)Print all pantone_values
        3)Print all ids greater than 3 on the console ==> id si 3 den buyuk olanları  yazdır
          Assert that there are 3 ids greater than 3
        4)Print all names whose ids are less than 3 on the console ==> id si 3 den kucuk olanları isimleri  yazd
          Assert that the number of names whose ids are less than 3 is 2
*/

    @Test
    public void get06() {
        //set the url
        spec.pathParam("first","unknown");

        //set the expected data

        //send the request

        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //body metodu ile sadece assertion yapabilirim, dataları dışarı alıp konsola yazdıramam.
        //jsonpath ile dataları dısarı alıp konsolda ada yazdırabilir veassertion yapabilirim
        response.then().body("data.id",hasSize(6));

        //Do Assertion
         assertEquals(200,response.getStatusCode());
         //response dakı dataları dısarı almak için jsonpath objesinin içine almam lazım
        // Response a baktıgımızda [] ile baslıyor burda bir list oldugunu anlıyoruz.
        // Response; .icerisinde Json lar olan bir collection döndürüyor burda

        JsonPath jsonPath=response.jsonPath();
        jsonPath.getList("data.pantone_value");
        System.out.println( jsonPath.getList("data.pantone_value"));

        assertEquals(200,response.getStatusCode());

        //id si 3den buyuk olanların ismini yazdır
        //findAll Collectionın (data) basldıgı yerden yazılır.
        //Benim listim data: dan baslıyor data.id =tum id listi döndürür.
        System.out.println( jsonPath.getList("data.id"));

        //elementlerin jsonların herbirini it temsil ediyor(Lambdada kı t gibi)
        //Buna growy languge deniyor
        //Burda it.id jsonlarda id lere bak ki degeri 3 den buyuk olsun.
      List<Integer> ids=jsonPath.getList("data.findAll{it.id>3}.id");

        System.out.println("3 den buyuk idler: " + ids);
      assertEquals(3, ids.size());

    //id si 3den kucuk olanların nameleri
        List<String> idsname=jsonPath.getList("data.findAll{it.id<3}.name");
        System.out.println("idsi 3 den kucuk olanların isimleri: "+idsname);

        assertEquals(2,idsname.size());

    }
}
