package apiTest.day7_POST_Request;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static org.testng.Assert.*;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class PostRequestDemo {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";

    }

    @Test
    public void postNewUser(){  // 1. yol body göndermek

        String jsonBody="{\n" +
                "  \"name\": \"KtMn\",\n" +
                "  \"email\": \"KtMn@TSK.com\",\n" +
                "  \"password\": \"12346789\",\n" +
                "  \"about\": \"WeWillWin\",\n" +
                "  \"terms\": \"10\"\n" +
                "}";

        Response response = given().accept(ContentType.JSON)  // hey api send me body as json format
                .and()
                .contentType(ContentType.JSON) // hey api I am sending json body
                .and()
                .body(jsonBody)
                .when()
                .post("/allusers/register");

        assertEquals(response.statusCode(),200); // asssert importuna static ekledik ve baştaki Assert gereksiz oldu
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void potNewUser2(){  // 2. yol map ile

        Map<String,Object> requestMap = new HashMap<>();

        requestMap.put("name","KtMn2");
        requestMap.put("email","KtMn2@TSK.com");
        requestMap.put("password","12346789");
        requestMap.put("about","WeWillWin2");
        requestMap.put("terms","3");

        Response response = given().accept(ContentType.JSON) // json bekliyorum
                .and()
                .contentType(ContentType.JSON) // json gönderiyorum
                .and()
                .body(requestMap) // oluşturduğumuz map i girdik  // serialization
                .when()
                .post("/allusers/register");// end pointi yazdık

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser3(){  // 3. yol başka bir classtaki private variableyi set etmek

        NewUserInfo newUserInfo = new NewUserInfo();
        newUserInfo.setName("care1"); // burda newuserinfo clasındaki private variableleri set ile değiştiriyoruz
        newUserInfo.setEmail("care1@gmail.com");
        newUserInfo.setPassword("123456789");
        newUserInfo.setAbout("ne");
        newUserInfo.setTerms("1");

        Response response = given().accept(ContentType.JSON) // json bekliyorum
                .and()
                .contentType(ContentType.JSON) // json gönderiyorum
                .and()
                .body(newUserInfo) // oluşturduğumuz map i girdik  // serialization
                .when()
                .post("/allusers/register");// end pointi yazdık

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));

    }

    @Test
    public void postNewUser4(){  // 4. yol başka bir class içindei constructor den faydalanarak

    NewUserInfo newUserInfo = new NewUserInfo("care2","care2@gmail.com","123456789","me","2");


        Response response = given().accept(ContentType.JSON) // json bekliyorum
                .and()
                .contentType(ContentType.JSON) // json gönderiyorum
                .and()
                .body(newUserInfo) // oluşturduğumuz map i girdik  // serialization
                .when()
                .post("/allusers/register");// end pointi yazdık

        assertEquals(response.statusCode(),200);
        response.prettyPrint();

        assertTrue(response.body().asString().contains("token"));



    }


}
