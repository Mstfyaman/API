package apiTest.day8_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;


public class PostExperience_2 {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void newExperince(){  // olmayan bir veri girdik

        String body = "{\n" +  // bu experience i oluşturduğumuz user e atayacağız. Kalıp swaggerden
                "  \"job\": \"Software Developer In Test\",\n" +
                "  \"company\": \"Harvard Bootcamp\",\n" +
                "  \"location\": \"England\",\n" +
                "  \"fromdate\": \"2022-08-25\",\n" +
                "  \"todate\": \"2023-08-25\",\n" +
                "  \"current\": \"true\",\n" +
                "  \"description\": \"Great Job\"\n" +
                "}";
        Response response = given().accept(ContentType.JSON)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAzIiwic3RhcnQiOjE2NzQ4Mzg5NTUsImVuZHMiOjE2NzU0NDM3NTV9.VZ0xsf7s9gezGbUq6FQpkKRZjpeHwhnbyibp1Q98-l4ucQKRI4sBOyExhFJlLfdldO_QxYzhMlM9Z-CvBgch-A")
                .body(body)  // yukarıdaki string
                .when().log().all()
                .post("/experience/add").prettyPeek(); // pretty. yazdırmak için

        // experience id 1 = 228
        // user id = 303
        // experience id 2 = 248    yukarıdaki bilgileri güncelleyerek user e 2. bir experince atadık

    }






}


