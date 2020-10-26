package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;


public class PostRequest02a extends TestBase{
    /*
     POST Scenario:
     Content type Json olsun  (Content Type demektir.)
     When
     POST request yolladigimda
     1) https://restful-booker.herokuapp.com/booking
     2) Request Body
     {
     "firstname": "Hasan",
     "lastname": "Kara",
     "totalprice": 123,
     "depositpaid": true,
     "bookingdates": {
     "checkin": "2020-05-02",
     "checkout": "2020-05-05"
     },
     "additionalneeds": "Wifi"
     }
     Then
     Status Code 200 olmali
     Response Body, Request Body ile ayni olduğunu verify ediniz
     *****Json formatlar icin obje olusturacagiz ve bu objeleri body olarak kullanacagiz
     */
    @Test
    public void post01(){
        /*
        "bookingdates": {
             "checkin": "2020-05-02",
             "checkout": "2020-05-05"
             },
         */
        Response response= createRequestBodyByJsonObjectClass();
        //reateRequestbodyByJsonObjectClass(); bu kismi testbase icinden cagirdik
        //normalde uzun uzun yazmak gerekiyordu.
        //PostRequest02'de uzunca yazilmisti PostRequest02a'da bu sekilde kisaltmis olduk
        response.prettyPrint();

        //statuscode 200 olmali
        response.then().assertThat().statusCode(200);//hard assertion

        //JsonPath kullanarak assertion
        JsonPath jsonPath= response.jsonPath();

        SoftAssert softAssert= new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("booking.firstname"), "Hasan");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"), "Kara");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"), 123);
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"), true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2020-05-02");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2020-05-05");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");


        softAssert.assertAll();



    }

}
