package test_data;

import java.util.HashMap;
import java.util.Map;

public class RestfulTestData {

    public Map<String,String> bookingdatesMethod(String checkin, String checkout){

        Map<String,String> bookingdates = new HashMap<String,String> ();
        bookingdates.put("checkin",checkin);
        bookingdates.put("checkout",checkout);

        return bookingdates;

    }

    public Map<String,Object> expectedDataMethod(String firstname,String lastname,Integer totalprice,
                                                 Boolean depositpaid,Map<String,String> bookingdates){

        Map<String,Object> expectedDataMap =new HashMap<>();
        expectedDataMap.put("firstname",firstname);
        expectedDataMap.put("lastname",lastname);
        expectedDataMap.put("totalprice",totalprice);
        expectedDataMap.put("depositpaid",depositpaid);
        expectedDataMap.put("bookingdates",bookingdates);

        return expectedDataMap;

    }

}
