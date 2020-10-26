package techproedbatch5;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import static io.restassured.RestAssured.*;
public class GetRequest11 extends TestBase{
    /*
    GSON 1) Json formatindaki datalari Java Object lerine donusturur. (De Serialization)
         2) Java Object lerini Json formatina donusturur. (Serialization)
         json --> GSON ---> Java (maplere yada listlere cevrilis.) // De Serialization
         Java --> GSON -->Json  // Serialization
    */
    @Test
    public void get01(){
        Response response = given().
                spec(spec03).
                when().
                get("/2");
        response.prettyPrint();
        HashMap <String,String > map = response.as(HashMap.class); //De Serialization
        System.out.println(map);
        System.out.println(map.keySet()); //id,completed, title ...
        System.out.println(map.values());//2.0,1.0, false ,....

        //completed key degerinin false oldugunu verify ediniz.

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(map.get("completed"),false,"False degerinde degil");
        //userId, title,ve id degerlerini verify ediniz.
        softAssert.assertEquals(map.get("userId"),1,"userId uyusmuyor");
        softAssert.assertEquals(map.get("id"),2,"id uyusmuyor.");
        softAssert.assertEquals(map.get("title"),"quis ut nam facilis et officia qui","Title uyusmuyor.");
        softAssert.assertAll();

        //Map objesini Json formatina cevirme
        Gson gson=new Gson();
        System.out.println(gson.toJson(map));//serialization
    }
}
