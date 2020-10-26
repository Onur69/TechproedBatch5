package techproedbatch5;

import io.restassured.response.Response;
import org.junit.Test;
import static io.restassured.RestAssured.*;

public class GetRequest01 {
    @Test
    public void getMethod01(){
        given(). //restAssured kutuphanesinden gelen bir method
                when(). //when den sonra --get, post, put,patch, delete -- methodlari calistirilir.
                //get("https://restful-booker.herokuapp.com").//get te sadece Endpoint kullanilir.
                get("https://restful-booker.herokuapp.com/booking").
                then().//dogrulama demek
                assertThat().
                statusCode(200);
    }
    //get ile aldimiz data'yi console'da gormek istiyoruz. Gelen data'yi bir container icerisine alip
    //yazdirmak gerekiyor. Bunun icinde response kullanarak postman'deki ciktinin aynisini elde edecegiz.
    //response body kismini yazdirir.
    @Test
    public void getMethod02(){
        Response response= given().
                                when().
                                    get("https://restful-booker.herokuapp.com/booking");
        //response body'i console'a yazdirmak icin
        response.prettyPrint();

        //status code console'a yazdirmak icin
        System.out.println(response.getStatusCode());

        //postman'deki status line'i console'da gormek icin
        System.out.println("Status line: "+ response.getStatusLine());

        //postman header kismindaki contenet type yazdirilir
        //1.yol
        System.out.println("Content Type1: "+response.getContentType());
        //2.yol
        System.out.println("Contenet Type2"+response.header("Content-Type"));

        //header daki tum bilgiler icin
        System.out.println(response.getHeaders());

        //headers'ta spesifik bilgi almak icin
        System.out.println(response.header("Date"));

        //
        response.
                then().
                assertThat(). //hard assert hata buldugunda devam etmez.
                statusCode(200).
                contentType("application/json; charset=utf-8"). //Negative senaryo "application/json; charset=utf-9"
                statusLine("HTTP/1.1 200 OK");

    }
}
