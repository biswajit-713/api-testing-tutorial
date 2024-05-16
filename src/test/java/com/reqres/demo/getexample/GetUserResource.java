package com.reqres.demo.getexample;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Test
public class GetUserResource {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void shouldListAllUsersFromSpecifiedPageInPagination() {
        given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                .body("per_page", equalTo(6))
                .body("total", equalTo(12))
                .body("data.size()", equalTo(6))
                .body("data[0]", hasKey("id"))
                .body("data[0]", hasKey("email"))
                .body("data[0]", hasKey("first_name"))
                .body("data[0]", hasKey("last_name"))
                .body("data[0]", hasKey("avatar"))
                .body("data.findAll {it.keySet().size() != 5}.size()", equalTo(0));
    }


}
