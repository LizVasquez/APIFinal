package runner;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class MyStepdefs {

    Response response;
    Map<String, String> data = new HashMap<>();

    @Given("tengo acceso a Todo.ly")
    public void tengoAccesoATodoLy() {
    }

    @When("envio una peticion POST al url {} con json")
    public void envioUnaPeticionPOSTAlUrlHttpTodoLyApiItemsJsonConJson(String url, String body) {
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                post(replaceAllData(url));

        response.then().log().all();
    }

    @Then("espero el codigo de respuesta {int}")
    public void esperoElCodigoDeRespuesta(int expectedResult) {
        response.then().
                statusCode(expectedResult);
    }


    @And("tengo el {} y lo guardo en {}")
    public void tengoElIdYLoGuardoEnID_ITEM(String property, String nameVar) {
        data.put(nameVar, response.then().extract().path(property) + "");
    }

    @When("envio una peticion PUT al url {} con json")
    public void envioUnaPeticionPUTAlUrlHttpTodoLyApiItemsID_ITEMJsonConJson(String url, String body) {
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                body(body).
                log().
                all().
                when().
                put(replaceAllData(url));

        response.then().log().all();
    }

    @When("envio una peticion GET al url {} con json")
    public void envioUnaPeticionGETAlUrlHttpTodoLyApiItemsID_ITEMJsonConJson(String url, String body) {
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                get(replaceAllData(url));

        response.then().log().all();
    }

    @When("envio una peticion DELETE al url {} con json")
    public void envioUnaPeticionDELETEAlUrlHttpTodoLyApiItemsID_ITEMJsonConJson(String url, String body) {
        response = given().
                auth().
                preemptive().
                basic("lizapi@liz.com", "123456").
                contentType(ContentType.JSON).
                log().
                all().
                when().
                delete(replaceAllData(url));

        response.then().log().all();
    }

    private String replaceAllData(String value) {
        for (String key : data.keySet()
        ) {
            value = value.replace(key, data.get(key));

        }
        return value;
    }
}
