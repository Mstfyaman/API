package apiTest.day4_JsonPath;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.Calendar;
import java.util.List;

import static io.restassured.RestAssured.baseURI;

public class UserWith_JsonPath {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";

    }

    @Test
    public void jsonPathTest1(){
    /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And user's name should be Thomas Eduson
    And user's id should be 111
    And user's email should be thomas@test.com
   */

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        JsonPath jsonPath = response.jsonPath();  // json objesi oluşturduk

        int idJson = jsonPath.getInt("id[0]");
        String nameJson = jsonPath.getString("name[0]");
        String emaiJson = jsonPath.getString("email[0]");

        Assert.assertEquals(idJson,111);
        Assert.assertEquals(nameJson,"Thomas Eduson");
        Assert.assertEquals(emaiJson,"thomas@test.com");

        response.prettyPrint();

    }

    @Test
    public void jsonPathTest2(){
        /*
    TASK
    Given accept type is json
    When user sends a GET request to /allusers/alluser
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    And second name should be isa akyuz
    And first user's experience jobs should be Junior Developer1, Junior Developer1, Junior Developer
     */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");


        JsonPath jsonPath = response.jsonPath();

        String secondJsonName = jsonPath.getString("name[1]");
        String firstUserExperience = jsonPath.getString("experience.job[0]");
                // experience arrayi içindeki job a gittik.))

        Assert.assertEquals(secondJsonName,"isa akyuz");
        Assert.assertEquals(firstUserExperience,"[Junior Developer1, Junior Developer1, Junior Developer]");

            // listli yapılmış hali experience in
        List<String> jobs = jsonPath.getList("experience.job[0]");
     //   Assert.assertEquals(firstUserExperience,jobs);

    }

    @Test
    public void jsonPathTest3(){
        /*
    TASK
    Given accept type is json
    And Path param user id is 111
    When user sends a GET request to /allusers/getbyid/{id}
    Then the status Code should be 200
    And Content type json should be "application/json; charset=UTF-8"
    Get user skills
    And user's first skill should be PHP

   */

        Response response = given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");


        JsonPath jsonPath = response.jsonPath();

        String userFirtSkill = jsonPath.getString("skills");
        System.out.println("userFirtSkill = " + userFirtSkill);
                // userFirtSkill = [[PHP, Java, Selenium, Cypress, SQL]]

        String FirtSkill = jsonPath.getString("skills[0][0]");
        System.out.println("FirtSkill = " + FirtSkill);
                // FirtSkill = PHP

    }







}
