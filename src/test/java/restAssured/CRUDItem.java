package restAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class CRUDItem {

    private Response response;
    //private String tokenVal;

   /* @Before

    public void getToken(){
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com","123456").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get("https://todo.ly/api/authentication/token.json");

        response.then().statusCode(200);
        tokenVal=response.then().extract().path("TokenString");
    }*/


    @Test
    public void crudItem(){
        JSONObject body= new JSONObject();
        body.put("Content","TestItem");

        //GET TOKEN
        /*response = given().
                header("Token",this.tokenVal).
                contentType(ContentType.JSON).
                body(body.toString()).
                log().all().
                when().
                post("https://todo.ly/api/items.json");*/


        //CREATE

        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                body(body.toString()).
                log().all().
                when().
                post("https://todo.ly/api/items.json");

        response.then().
                statusCode(200).
                body("Content", equalTo("TestItem")).
                log().all();


        //UPDATE

        int idItem = response.then().extract().path("Id");
        body.put("Content", "ItemUpdate");
        body.put("Priority","5");
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                body(body.toString()).
                log().all().
                when().
                    put("https://todo.ly/api/items/"+idItem+".json");

        response.then().
                log().all().
                statusCode(200).
                body("Content", equalTo("ItemUpdate")).
                body("Priority", equalTo(5));

        //DELETE

        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                body(body.toString()).
                log().all().
                when().
                delete("https://todo.ly/api/items/"+idItem+".json");

        response.then().
                log().all().
                statusCode(200).
                body("Content", equalTo("ItemUpdate")).
                body("Priority", equalTo(5)).
                body("Deleted",equalTo(true));


        //READ

        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                body(body.toString()).
                log().all().
                when().
                get("https://todo.ly/api/items.json");

        response.then().
                statusCode(200).
                log().all();





    }




}
