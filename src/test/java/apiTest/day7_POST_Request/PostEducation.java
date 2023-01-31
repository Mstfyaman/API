package apiTest.day7_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.*;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.hamcrest.Matchers.*;

public class PostEducation {

    @Test
    public void postNewUser(){   // bu test patlıyor

        NewUserInfo newUserInfo = new NewUserInfo("KtM2","Ktm2@TSK.com","123456789","WeWillWin2","102");


        Response response = given().accept(ContentType.JSON) // json bekliyorum
                .and()
                .contentType(ContentType.JSON) // json gönderiyorum
                .and()
                .body(newUserInfo) // oluşturduğumuz map i girdik  // serialization
                .when()
                .post("/allusers/register");// end pointi yazdık

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token = response.path("token");

        String educationBody ="{\n" +
                "  \"school\": \"Oxford\",\n" +
                "  \"degree\": \"Developer In Test\",\n" +
                "  \"study\": \"Study\",\n" +
                "  \"fromdate\": \"2022-07-25\",\n" +
                "  \"todate\": \"2023-03-25\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"QA Engineer Bootcamp\"\n" +
                "}";

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .when().post("/education/add");

        assertEquals(response.statusCode(),200);
        response.prettyPrint();
    }

    @Test
    public void postNewUserAndVerify(){  // bu da patlıyor .?
         String name = "testerr11122";
         String email ="testerr1112@gmail.com";
         String password ="123456798";
         String about="About me";
         String terms="7";

         Map<String,Object> requestMap= new HashMap<>();
        requestMap.put("name",name);
        requestMap.put("email",email);
        requestMap.put("password",password);
        requestMap.put("about",about);
        requestMap.put("terms",terms);

        Response response = given().accept(ContentType.JSON) // json bekliyorum
                .and()
                .contentType(ContentType.JSON) // json gönderiyorum
                .and()
                .body(requestMap) // oluşturduğumuz map i girdik  // serialization
                .when()
                .post("/allusers/register");// end pointi yazdık

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        String token = response.path("token");
       // String userID = response.path("userid");

        Map<String,Object> educationBody = new HashMap<>();
        educationBody.put("school","Oxford");
        educationBody.put("degree","Developer In Test");
        educationBody.put("study","Study");
        educationBody.put("fromdate","2022-07-25");
        educationBody.put("todate","2023-03-25");
        educationBody.put("current","true");
        educationBody.put("description","QA Engineer Bootcamp");

        response = given().accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body(educationBody)
                .and()
                .queryParam("token",token)
                .when().post("/education/add");

        response.prettyPrint();
        assertEquals(response.statusCode(),200);

        // verify body

        int id = response.path("id");
        response = given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("edication/getbyid/{id}");


        // verify with path

        assertEquals(response.path("school"),"Oxford");

        // verify using hamcrest matcher
        given().accept(ContentType.JSON)
                .and()
                .queryParam("token",token)
                .and()
                .pathParam("id",id)
                .when()
                .get("edication/getbyid/{id}")
                .then()
                .assertThat()
                .body("school",equalTo("Oxford"));


    }

}
