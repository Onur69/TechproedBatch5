package techproedbatch5;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

public class PostRequest03b extends TestBase{

    @Test
    public void post01(){
        Response response=createRequestBodyByMap();
        //createRequestBodyByMap(); bu kismi testbase altinda olusturduk ordan cagirdik
        response.prettyPrint();
        //status code 200 olmali
        response.
                then().
                assertThat().
                statusCode(200); //hard assrtion
        //JsonPath kullanarak assertion
        JsonPath jsonPath =response.jsonPath();
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("booking.firstname"),requestBodyMap.get("firstname"));
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),requestBodyMap.get("lastname"));
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),requestBodyMap.get("totalprice"));
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),requestBodyMap.get("depositpaid"));
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),bookingDatesMap.get("checkin"));
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),bookingDatesMap.get("checkout"));
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),requestBodyMap.get("additionalneeds"));
        softAssert.assertAll();
    }
}
