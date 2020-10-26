package techproedbatch5;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

public class PostRequest03a extends TestBase{

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
        softAssert.assertEquals(jsonPath.getString("booking.firstname"),"Hasan");
        softAssert.assertEquals(jsonPath.getString("booking.lastname"),"Kara");
        softAssert.assertEquals(jsonPath.getInt("booking.totalprice"),123);
        softAssert.assertEquals(jsonPath.getBoolean("booking.depositpaid"),true);
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkin"),"2020-05-02");
        softAssert.assertEquals(jsonPath.getString("booking.bookingdates.checkout"),"2020-05-05");
        softAssert.assertEquals(jsonPath.getString("booking.additionalneeds"),"Wifi");
        softAssert.assertAll();
    }

}
