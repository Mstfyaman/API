package apiTest.day8_PutPatchDelete;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class DeleteExperience_5 {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";
    }

    @Test
    public void deleteExperience(){  // seçtiğimiz experiencey i siliyoruz


        Response response = given().accept(ContentType.JSON)
                .pathParam("id",248)
                .queryParam("token","eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdXQiOiJhRm0iLCJsaW5rIjoia3JhZnR0ZWNoZXhsYWIuY29tIiwidXNlcmlkIjoiMzAzIiwic3RhcnQiOjE2NzQ4Mzg5NTUsImVuZHMiOjE2NzU0NDM3NTV9.VZ0xsf7s9gezGbUq6FQpkKRZjpeHwhnbyibp1Q98-l4ucQKRI4sBOyExhFJlLfdldO_QxYzhMlM9Z-CvBgch-A")
                .when().log().all()
                .delete("/experience/delete/{id}");
            // delete dikkat !!!!


    }


}
