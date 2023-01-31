package apiTest.day8_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PutExperience_3 {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }


    @Test
    public void uptadeExperience(){  // var olan bir experience verisini uptade ettik. güncelledik

        String body = "{\n" +  // swaggerdeki experince put daki body yi getirdik
                "  \"job\": \"Junior Developer In Test\",\n" +
                "  \"company\": \"Karaelmas Uni\",\n" +
                "  \"location\": \"Zonguldak\",\n" +
                "  \"fromdate\": \"2020-12-01\",\n" +
                "  \"todate\": \"2021-12-02\",\n" +
                "  \"current\": \"false\",\n" +
                "  \"description\": \"GREAT BOOTCAMP\"\n" +
                "}";
        Response response= given().accept(ContentType.JSON)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAzIiwic3RhcnQiOjE2NzQ4Mzg5NTUsImVuZHMiOjE2NzU0NDM3NTV9.VZ0xsf7s9gezGbUq6FQpkKRZjpeHwhnbyibp1Q98-l4ucQKRI4sBOyExhFJlLfdldO_QxYzhMlM9Z-CvBgch-A")
                    // parametre olarak token ve id istiyor.
                .queryParam("id",228)
                .body(body)
                .when().log().all()
                .get("/experience/updateput").prettyPeek();



    }

}
