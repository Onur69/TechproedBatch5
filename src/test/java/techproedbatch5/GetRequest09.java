package techproedbatch5;

import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest09 extends TestBase {

    @Test
    public void get01(){
        Response response= given().
                spec(spec02).
                when().get();
        response.prettyPrint();

        //Json path objesi olusturun
        JsonPath jsonPath= response.jsonPath();

        //tum employee isimlerini console'a yazdirmak

        System.out.println(jsonPath.getString("data.employee_name"));
        //System.out.println(jsonPath.getList("data.employee_name"));

        //ikinci iscinin isminin Garrett Winters  oldugunu verify(soft) ediniz
        assertEquals("isim istenildigi gibi degil","Garrett Winters",
                jsonPath.getString("data[1].employee_name")); //bu method hard assertion
        /*Soft assertion  icin 3 adimi takip etmek gerekir
            1)SoftAssert class indan bir obje (softAssert) uretilir
            2)Objeyi kullanarak assertion yapilir.
            3)SoftAssert.assertAll(); ile test bitirilir.
         */
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertEquals(jsonPath.getString("data[1].employee_name"),
                "Garrett Winters","isim istenildigi gibi degil");

        //Assertion denilirse bu hard test demektir
        //verification denilirse bu (verify) soft test demektir

        //iscilerin arasinda Herrod Chandler oldugunu verify ediniz

        softAssert.assertTrue(jsonPath.getList("data.employee_name").
                contains("Herrod Chandler"),"Herrod Chandler isminde isci yoktur");

        //24 tane isci oldugunu verify yapiniz
        softAssert.assertEquals(jsonPath.getList("data.employee_name").size(),
                24 ,"isci sayisi 24 degildir");

        //7. iscinin maasinin 137500 oldugunu verify ediniz
        softAssert.assertEquals(jsonPath.getString("data[6].employee_salary"),
                "137500" ,"isci maasi yanlis");
        softAssert.assertAll();
    }

}
