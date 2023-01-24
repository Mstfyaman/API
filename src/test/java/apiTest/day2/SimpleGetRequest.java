package apiTest.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.annotation.Repeatable;

public class SimpleGetRequest {

  //  String petStoreURL = "https://petstore.swagger.io/v2/store/inventory";
    String petStoreURL = "https://petstore.swagger.io/v2/pet/5";
  //  String petStoreURL = "https://petstore.swagger.io/v2";
    String kraftURL = "https://www.krafttechexlab.com/sw/api/v1/";


    @Test
    public void test1(){
      //  Response response = RestAssured.get(petStoreURL);// yukarıdaki url yi aldık.
        Response response = RestAssured.get(petStoreURL+ "/store/inventory");// yukarıdaki url yi aldık.

        // print status code
        System.out.println("response.statusCode() = " + response.statusCode());  // status code verir 200,201, 404 gibi

        // print body
        response.prettyPrint();   // sorgu yaptığımız body yi aldık
        //    {
        //    "sold": 4,
        //    "string": 566,
        //    "In Transit": 1,
        //    "pending": 5,
        //    "available": 397,
        //    "active": 7,
        //    "talking": 1,
        //    "good": 1,
        //    "totvs1": 1
        //   }

    }

    @Test
    public void test2(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL);

        System.out.println("response.statusCode() = " + response.statusCode());

        Assert.assertEquals(response.statusCode(),200);
        response.prettyPrint();

      //  Assert.assertEquals(response.contentType(),"application/json");
    }

    @Test
    public void test3(){

        // verify test case with using RestAssured Library
        RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL)
                .then().assertThat().statusCode(200)
                .and()
                .contentType("application/json");

    }

    @Test
    public void test4(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(petStoreURL);

        Assert.assertEquals(response.statusCode(),200);
        System.out.println("response.body().asString() = " + response.body().asString());

      // Assert.assertTrue(response.body().asString().contains("395"));  kodu sürekli değiştiği için hata veriyor.
    }



}
