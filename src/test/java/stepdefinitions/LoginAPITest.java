package stepdefinitions;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.bouncycastle.asn1.x500.style.RFC4519Style.st;

public class LoginAPITest{

    //post request with login URL and the method body


//possitive login sceanrio
    @Test
    void loginWithValidCredentials() {


        HashMap data = new HashMap<>();
        data.put("email", "test@gmail.com");
        data.put("password", "test");

        given()
                .contentType("application/json")
                .body(data)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .statusCode(200)
                .log().all();
    }

    @Test
//negative testcase
        void loginWithInvalidCredentials(){



            HashMap data=new HashMap<>();
            data.put("email","test@gmail.com");
            data.put("password","test1234");

            given()
                    .contentType("application/json")
                    .body(data)
                    .when()
                    .post("https://reqres.in/api/login")
                    .then()
                    .statusCode(400)
                    .log().all();


}









    }

