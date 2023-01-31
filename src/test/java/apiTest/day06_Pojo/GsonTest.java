package apiTest.day06_Pojo;

import com.google.gson.Gson;
import org.testng.annotations.Test;

import java.util.Map;

public class GsonTest {

     /*
    {
    "id": 9222968140497198741,
    "username": "Jake23",
    "firstName": "Jake",
    "lastName": "Master",
    "email": "jake@gmail.com",
    "password": "Test1234",
    "phone": "55512345",
    "userStatus": 21
    }
     */

    @Test
    public void jsonToMap(){
        Gson gson = new Gson();
        String myJsonBody= " {\n" +
                "    \"id\": 9222968140497198741,\n" +
                "    \"username\": \"Jake23\",\n" +
                "    \"firstName\": \"Jake\",\n" +
                "    \"lastName\": \"Master\",\n" +
                "    \"email\": \"jake@gmail.com\",\n" +
                "    \"password\": \"Test1234\",\n" +
                "    \"phone\": \"55512345\",\n" +
                "    \"userStatus\": 21\n" +
                "    }";

        System.out.println("myJsonBody = " + myJsonBody);

        // gson converting to map
        Map<String,Object> dataMap = gson.fromJson(myJsonBody,Map.class);
        System.out.println("dataMap : "+ dataMap);

        // gson converting to object class
        PetStoryUser oneUser= gson.fromJson(myJsonBody,PetStoryUser.class);
        System.out.println("oneUser = " + oneUser);

        // Serialization
        // Java collection or POJO to Json
        PetStoryUser petStoryUser = new PetStoryUser(9,"Jake23","Jake",
                "Master","jake@gmail.com","Test1234","55512345",21);

        String jsonUser = gson.toJson(petStoryUser);
        System.out.println("jsonUser = " + jsonUser);


    }

}
