package techproedbatch5;
import io.restassured.RestAssured.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBase {
        /*
       When I send GET Request to URL
       http://dummy.restapiexample.com/api/v1/employees
       Then
       Status code is 200
       1)10'dan buyuk tum id'leri console'a yazdir
       10'dan buyuk 14 tane id oldugunu verify et
       2)30'dan kucuk tum yaslari console'a yazdir
       30 dan kucuk en buyuk yasin 23 oldugunu verify et
       3)Maasi 350.000'den cok olan iscilerin isimlerini console'a yazdir
       Charde Marshall'in maasinin 350.000'den buyuk oldugunu verify et
     */
    @Test
    public void get01(){
        Response response=given().
                spec(spec02).
                when().get();
        response.prettyPrint();

        // Status code is 200
        response.
                then().
                assertThat().
                statusCode(200);
        //1)10'dan buyuk tum id'leri console'a yazdir

        JsonPath jsonPath= response.jsonPath();
        SoftAssert softAssert= new SoftAssert();

        List<String> idList= jsonPath.getList("data.findAll{Integer.valueOf(it.id)>10}.id");
        //butun herseyi al ve buradaki idleri stringden integera cevir ve 10'dan buyukleri getir.

        System.out.println(idList);

        //10'dan buyuk 14 tane id oldugunu verify et
        softAssert.assertEquals(idList.size(), 14,"eleman sayisi 14 degil");

        //2)30'dan kucuk tum yaslari console'a yazdir
        List<String> yasList= jsonPath.getList("data.findAll{Integer.valueOf(it.employee_age)<30}.employee_age");
        System.out.println(yasList);
        //30 dan kucuk en buyuk yasin 23 oldugunu verify et
        Collections.sort(yasList);//listi kucukten buyuge siralamak icin
        System.out.println(yasList);

        softAssert.assertTrue(yasList.get(yasList.size()-1).equals("23"), "yas istenilen degerde degildir");

        //3)Maasi 350.000'den cok olan iscilerin isimlerini console'a yazdir
        List<String> nameList=jsonPath.getList("data.findAll{Integer.valueOf(it.employee_salary)>350000}.employee_name");
        System.out.println(nameList);

        //Charde Marshall'in maasinin 350.000'den buyuk oldugunu verify et
        softAssert.assertTrue(nameList.contains("Charde Marshall"),"aranan kisi listede bulunamadi");

        softAssert.assertAll();
    }
}
