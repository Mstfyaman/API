package apiTest.day5_HamcrestMatcher;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class JsonToJavaCollection {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";

    }


    @Test
    public void UserToMap(){

        Response response = given().accept(ContentType.JSON)
                .when().get("https://demoqa.com/Account/v1/User/11%22");

        Assert.assertEquals(response.statusCode(),401);

        Map<String,Object> jsonMap = response.body().as(Map.class);
        System.out.println("jsonMap = " + jsonMap);

        // verify the message
        String message = (String) jsonMap.get("message");
        System.out.println("message = " + message);
        Assert.assertEquals(message,"User not authorized!");

        // verify the code
        String code = (String) jsonMap.get("code");
        System.out.println("code = " + code);
        Assert.assertEquals(code,"1200");
    }


    @Test
    public void AllUserToMap(){
        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);

        // we need to de-serialiaze Json response to Java Collection
        // birden fazla json body bulundurdugundan list of map yapmamız gerekiyor

        List<Map<String,Object>> allUsersMap= response.body().as(List.class);

        System.out.println("allUsersMap = " + allUsersMap);

        // ikinci kullanıcının adini assert edelim
        System.out.println("allUsersMap.get(1).get(\"name\") = " + allUsersMap.get(1).get("name")); // 2.kullanıcının adını yazdırdık.
                // isa akyuz

        String name = (String) allUsersMap.get(1).get("name");
        Assert.assertEquals(name,"isa akyuz");

        System.out.println("allUsersMap.get(0).get(\"skills\") = " + allUsersMap.get(0).get("skills"));

        List<String> skills = (List<String>) allUsersMap.get(0).get("skills");
                //[PHP, Java, Python, Html, CSS3]

        Assert.assertEquals(skills.get(0),"PHP");

        List<Map<String,Object>> experienceListMap = (List<Map<String, Object>>) allUsersMap.get(0).get("experience");

        System.out.println("experienceListMap = " + experienceListMap);
              // [{id=38.0, job=Junior Developer1, company=Kraft Techex1, location=USA2, description=Description2, fromdate=2019-01-01, todate=2022-10-09, date=2022-09-05 18:10:26}, {id=56.0, job=Junior Developer1, company=Kraft Techex, location=USA, description=Description, fromdate=2020-01-10, todate=2022-10-09, date=2022-10-09 17:52:44}, {id=189.0, job=Junior Developer, company=Kraft Techex, location=USA, description=Description, fromdate=2000-01-01, todate=2023-01-21, date=2023-01-08 17:46:07}]

        System.out.println("experienceListMap.get(1).get(\"job\") = " + experienceListMap.get(1).get("job"));
               // Junior Developer1
    }


}
