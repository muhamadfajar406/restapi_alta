package starter.account.login;

import io.restassured.RestAssured;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.json.simple.JSONObject;
import starter.utils.Endpoint;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class Login {

    Endpoint endpoint = new Endpoint();
    JSONObject requestParams;


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
