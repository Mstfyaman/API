package apiTest.day3;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class UserGetRequestWithPathMethod {

    @BeforeClass
    public void beforeclass() {
        baseURI = "https://www.krafttechexlab.com/sw/api/v1";

    }

    @Test
    public void testWithPathMethod(){
        Response response = given().accept(ContentType.JSON)
                .pathParam("id",111)
                .when().log().all()
                .get("/allusers/getbyid/{id}");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");

        // print each value
        System.out.println("response.body().path(\"name\").toString() = " + response.body().path("name").toString());
            // response.body().path("name").toString() = [Thomas Eduson]
            // body içindeki name i bize getirdi

        System.out.println("response.path(\"id\").toString() = " + response.path("id").toString());
                // response.path("id").toString() = [111]
                // body yi kullanmadan da veriyor sadece path le

        System.out.println("response.path(\"job\").toString() = " + response.path("job").toString());
                // response.path("job").toString() = [Developer]

        // değişkenlere atadıkki assert edebilelim. yapı array içerisinde olduğundan arrayi []  belirtmek gerekiyor.
        int id = response.path("id[0]");
        String name = response.path("name[0]");
        String job = response.path("job[0]");

        Assert.assertEquals(id,111);
        Assert.assertEquals(name,"Thomas Eduson");
        Assert.assertEquals(job,"Developer");

    }

    @Test
    public void test1(){
        /*
    TASK
    Given accept type json
    And query  parameter value pagesize 50
    And query  parameter value page 1
    When user sends GET request to /allusers/alluser
    Then response status code should be 200
    And response content-type application/json; charset=UTF-8
    Verify the first id is 1
    Verify the first name is aFm
    Verify the last id is 102
    Verify the last name is GHAN
     */

        Response response = given().accept(ContentType.JSON)
                .queryParam("pagesize",50)
                .queryParam("page",1)
                .when().log().all()
                .get("/allusers/alluser");

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=UTF-8");
        Assert.assertEquals(response.getHeader("Content-Type"),"application/json; charset=UTF-8");

        int firstId = response.path("id[0]");
        String firstName = response.path("name[0]");

        int lastId = response.path("id[-1]");    // son indexi almak için -1 i böyle yaptık
        String lastName = response.path("name[-1]");  // son index i almak için böyle yaptık


        Assert.assertEquals(firstId,1);
        Assert.assertEquals(firstName,"aFm");
        System.out.println("firstName = " + firstName);

        Assert.assertEquals(lastId,102);
        Assert.assertEquals(lastName,"GHAN");
        System.out.println("lastName = " + lastName);

    }

    @Test
    public void demoqaTest(){
        /*
    Given accept type json
    When user sends a get request to https://demoqa.com/BookStore/v1/Books
    Then status code should be 200
    And content typr should be application/json; charset=utf-8
    And the first book isbn should be 9781449325862
    And the first book publisher should be O'Reilly Media

     */
        String exlabURL ="https://demoqa.com/BookStore/v1/Books";  // burda before classı 1 defaya mahsus kullanmadık. url yi kendimiz yazdık

        Response response = given().accept(ContentType.JSON)
                .when()
                .get(exlabURL);

        Assert.assertEquals(response.statusCode(),200);
        Assert.assertEquals(response.contentType(),"application/json; charset=utf-8");


        String isbnNo = response.path("books.isbn[0]");
                // aşağıdaki tabloda önce books un içine girmemiz ve devamında isbn ye ulaşmamız gerekiyordu
                // bu yüzden books.isbn[0]  yazıp books'un isbn sine ve [0] ile ilk isbn sine gittik. Aşağıda daha isbn vardı.
        System.out.println("isbnNo = " + isbnNo);
        Assert.assertEquals(isbnNo,"9781449325862");

        // {
        //    "books": [
        //        {
        //            "isbn": "9781449325862",
        //            "title": "Git Pocket Guide",
        //            "subTitle": "A Working Introduction",
        //            "author": "Richard E. Silverman",
        //            "publish_date": "2020-06-04T08:48:39.000Z",
        //            "publisher": "O'Reilly Media",
        //            "pages": 234,
        //            "description": "This pocket guide is the perfect on-the-job companion to Git, the distributed version control system. It provides a compact, readable introduction to Git for new users, as well as a reference to common commands and procedures for those of you with Git exp",
        //            "website": "http://chimera.labs.oreilly.com/books/1230000000561/index.html"
        //        },



        String publisherName = response.path("books.publisher[0]");
        System.out.println("publisherName = " + publisherName);
        Assert.assertEquals(publisherName,"O'Reilly Media");

     //   response.prettyPrint();

    }







}
