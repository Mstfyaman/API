package apiTest.day8_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class PatchExperience_4 {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void patchExperience(){

        String body = "{\n" +  // bu body yi experience patch bölümden aldık. ama kısmı değişiklik yapacağımızdan biraz kırptık
                "  \"job\": \"Developer In Test\",\n" +
                "  \"company\": \"ETIYA\",\n" +
                "  \n" +
                "  \"fromdate\": \"2020-12-01\",\n" +
                "  \"todate\": \"2023-04-04\",\n" +
                "  \"current\": \"true\"\n" +
                "\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",248)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAzIiwic3RhcnQiOjE2NzQ4Mzg5NTUsImVuZHMiOjE2NzU0NDM3NTV9.VZ0xsf7s9gezGbUq6FQpkKRZjpeHwhnbyibp1Q98-l4ucQKRI4sBOyExhFJlLfdldO_QxYzhMlM9Z-CvBgch-A")
                .body(body)
                .when().log().all()
                .get("/experience/updatepatch/{id}").prettyPeek();


    }




}
