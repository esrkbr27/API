package get_request;

import base_url.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {

    @Test
    public void get11()  {

        /*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status //en az bir tanesi active mi
    And
        "Sujata Chaturvedi", "Navin Panicker"  "Bhadran Mehra LLD"
    And
        The female users are less than or equals to male users
 */

        //set the url

        spec.pathParam("first","users");

        //set the expected data


        //send the request and get the response
        Response response= given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        //Do Assertion

        response.then().assertThat().statusCode(200).body("meta.pagination.limit",equalTo(10),
                "meta.pagination.links.current",equalTo("https://gorest.co.in/public/v1/users?page=1"),
                 "data",hasSize(10),
                 "data.status",hasItem("active"),
                 "data.name",hasItems("Sujata Chaturvedi", "Navin Panicker","Bhadran Mehra LLD"));

        //1.yol;
        JsonPath json=response.jsonPath();
           List<String> genders= json.getList("data.gender");
        System.out.println(genders);

           int male=0;
           int female=0;
        for (String w: genders) {
            if(w.equals("male")){
                male++;
            }
            else if(w.equals("female")){
                female++;
            }
        }

        assertTrue(female<=genders.size()-female);

        //2.yol; Kadın ve erkek sayılarını Groovy ile bulalım.

        //Listin içindekı gender=female olan jsonları bul al demek
        //. name deyınce gender=female olan jsondakı nameleri liste attık

        List<String> femalenames=response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println("female sayısı: " + femalenames.size());

        List<String> malenames=response.jsonPath().getList("data.findAll{it.gender=='male'}.name");
        System.out.println("male sayısı: " + malenames.size());

        assertTrue(femalenames.size()<=malenames.size()-1);





    }
}
