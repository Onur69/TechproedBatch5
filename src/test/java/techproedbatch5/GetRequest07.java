package techproedbatch5;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertTrue;


public class GetRequest07 extends TestBase{
    @Test
    public void get01(){
        Response response= given().
                spec(spec01).
                when().
                get("/booking?firstname=Susan&depositpaid=false");
        response.prettyPrint();

        assertTrue(response.getBody().asString().contains("bookingid"));

    }
        @Test
    public void get02(){
        //spec01.queryParam("firstname", "Susan");
        //spec01.queryParam("lastname","Ericson");
        spec01.queryParams("firstname","Susan",
                        "lastname","Ericsson");

        Response response= given().spec(spec01).get("/booking");
        assertTrue(response.getBody().asString().contains("bookingid"));//aranani bulamayinca assertFalse ile halledilebilir
        }
}
