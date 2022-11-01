package get_request;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
/*
1)Postman; manuel API testleri için kullanılır.
2)Otomasyon testleri için de Rest Assured Library kullanılır.
3)Otomasyon testlerimizi yaparken asagıdakı adımları izleriz ;
  a)İlk olarak gereksinimleri anlamak
  b)Test case yazılır
     1)Test Case yazımında "Gherkin" dilini kullanırız
       Bizler yazılım diline hakım olsak da karsımızdakı kişiler hakım olmayabilir ama Gherkin ile
     yazılan testleri anlamakta zorluk cekmeyecekler.
     Gherkin dilinde kullanacagımız keywordler:

     -Given :(önkoşullar)
     -When  :(Yapılacak aksiyonlar için (get(),post(),patch(), put() ve delete () için kullanılır)
     -Then  : İstek yaptıktan sdonra (request gönderdikten sonra) doğrulama
     -And   :Coklu işlemlerde kullanılır

    c)Test kodları yazılır.

      i) Set the URL,
      ii) Set the expected data (beklenen datanın oluşturulması) Post,Put,Patch
      iii)Type code to send request (Talep göndermek için kod yazımı)
      iv)Do Assertion (doğrulama yapmak)


 */
    /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
        (url e bir sorgu gönderin)
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
        (Get Request yolladıgımızda dönen sonucun
        status kodunun 200, contetnt typenın JSON
        STATUS Line HTTP/1.1 200 OK oldugunu test edin

 */

    @Test
    public void get01() {
     //   i) Set the URL
        String url=" https://restful-booker.herokuapp.com/booking/102";

     // ii) Set the expected data (beklenen datanın oluşturulması)

     //Bizden post, put ya da patch istenmediği için bu casede kullanmayacagız.

     //   iii)Type code to send request (Talep göndermek için kod yazımı)

    Response response= given().when().get(url);
    response.prettyPrint();

    //    iv)Do Assertion (doğrulama yapmak)
        response.then().assertThat().statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");

        //Status Code yazdırın
        System.out.println("Status Code: " + response.getStatusCode());

        //Content type komsola yazdırın

        System.out.println("Content Type: " + response.getContentType());

        //Status line konsola yazdıralım

        System.out.println("Status Line: " + response.getStatusLine());

       //Header  konsola yazdıralım

        System.out.println("Header: " + response.getHeader("Server"));

        //Headers konsola yazdıralım

        System.out.println("Headers: " + response.getHeaders());

        //Time  konsola yazdıralım

        System.out.println("Time: " + response.getTime());


    }
}
