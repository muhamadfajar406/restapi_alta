package starter.utils;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;

import java.net.URI;
import java.net.URL;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Endpoint {



    Endpoint endpoint = new Endpoint();
    JSONObject requestParams;
    private URL LOGIN;

    @Step
    public void setBodyReq(){
        requestParams = new JSONObject();
        requestParams.put("userName","");
        requestParams.put("password","");
    }

    @Step
    public void hitEndpoindLogin(){
        SerenityRest
                .given()
                .header("Content-Type","application/json")
                .body(requestParams.toString())
                .when()
                .post(endpoint.LOGIN)
                .then()
                .statusCode(200);
    }

    @Step
    public void hitEndpoindLoginRA(){
        RestAssured
                .given()
                .header("Content-Type","application/json")
                .body(requestParams.toString())
                .when()
                .post(endpoint.LOGIN)
                .then()
                .statusCode(200);
    }

    @Step
    public void valdateEndpointLogin(){
        SerenityRest
                .then()
                .body(matchesJsonSchemaInClasspath("JSONSchema/login.json"));

    }

}
