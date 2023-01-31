package apiTest.day8_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class PostNewUser_1 {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newUser(){

    String body= "{\n" +   // swaggerdeki kalıbı aldık.  Kendimiz yeniden doldurduk
            "  \"name\": \"KemalTanca\",\n" +
            "  \"email\": \"Tanca@krafttech.com\",\n" +
            "  \"password\": \"123456789\",\n" +
            "  \"about\": \"Giyim\",\n" +
            "  \"terms\": \"10\"\n" +
            "}";

    Response response = given().accept(ContentType.JSON)
            .body(body) // stringi buraya attık
            .when().log().all()
            .post("/allusers/register").prettyPeek();


        String token = response.path("token");
        System.out.println("token = " + token); // put-patch-delete gibi işlemler için token gerekli.


        //token = eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAzIiwic3RhcnQiOjE2NzQ4Mzg5NTUsImVuZHMiOjE2NzU0NDM3NTV9.VZ0xsf7s9gezGbUq6FQpkKRZjpeHwhnbyibp1Q98-l4ucQKRI4sBOyExhFJlLfdldO_QxYzhMlM9Z-CvBgch-A
        // id = 303

    }






}
