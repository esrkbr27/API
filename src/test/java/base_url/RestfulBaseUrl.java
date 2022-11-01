package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

public class RestfulBaseUrl {

    protected RequestSpecification spec;

    @Before
    public void setUp () {
        spec=new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }


}
