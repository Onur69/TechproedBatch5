package techproedbatch5;
import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class GetRequest02 {
    /*Positive Scenario:
    when() Bir Get Request aşagida verilen Endpoint e yollanir.
            "https://restful-booker.herokuapp.com/booking"
    And() (Kabul edilecek )Accept Type i "aplication/json" dir
    then() status code 200 dur.
            and() content type "application/json" dir.*/

    @Test
    public void get01() {
        given().
                accept("application/json").
                when().
                get("https://restful-booker.herokuapp.com/booking").
                then().
                assertThat().
                statusCode(200).
                contentType("application/json");
    }
    @Test
    public void get02() {
        //Negative Scenario:
        //when() Bir Get Request aşagida verilen Endpoint e yollanir.
        //    "https://restful-booker.herokuapp.com/booking/1001"
        //    And() (Kabul edilecek )Accept Type i "aplication/json" dir
        //    then() status code 404 dur.
        Response response = given().
                accept("application-json").
                when().
                get("https://restful-booker.herokuapp.com/booking/1001");
        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(404);
        //contentType("application-json");//negative scenario larda content type olmaz.
        //content-type "text/plain; charset=utf-8" olur cunku

        //Negative Scenatio:
        //when() Bir Get Request aşagida verilen Endpoint e yollanir.
        //    "https://restful-booker.herokuapp.com/booking/1001"
        //      then() status code 404 dur.
        //      And() Response Body de "Not Found" var
        //      And () Response Body de "Techproed" yok
    }
        @Test
        public void get03(){
            Response response =given().
                    when().
                    get("https://restful-booker.herokuapp.com/booking/1001");
            response.prettyPrint();
            assertEquals(404,response.getStatusCode());
            assertTrue(response.asString().contains("Not Found")); //asString ile response String methoduna donusturulur.
            assertFalse(response.asString().contains("Techproed"));

        }
}
