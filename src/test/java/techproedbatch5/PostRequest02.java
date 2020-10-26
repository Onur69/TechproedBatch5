package techproedbatch5;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBase{
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

        JSONObject jsonBookingDatesBody= new JSONObject();

        jsonBookingDatesBody.put("checkin", "2020-05-02");
        jsonBookingDatesBody.put("checkout", "2020-05-05");

        JSONObject jsonRequestBody= new JSONObject();

        jsonRequestBody.put("firstname", "Hasan");
        jsonRequestBody.put("lastname", "Kara");
        jsonRequestBody.put("totalprice",123);
        jsonRequestBody.put("depositpaid",true);
        jsonRequestBody.put("bookingdates",jsonBookingDatesBody);
        jsonRequestBody.put("additionalneeds", "Wifi");

        Response response= given().contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(jsonRequestBody.toString()).//
                when().post("/booking");

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
