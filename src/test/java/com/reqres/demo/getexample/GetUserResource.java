package com.reqres.demo.getexample;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@Test
public class GetUserResource {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
        // in a scenario where you are testing your own system, you may want to create the data during the class setup
        // you may establish a connection to database
        // you may data from a resource file and insert into database
        // the following example demonstrates how you would read data from a csv from resources folder and
        // insert into database where actual insertion process is faked


    }

    @Test
    public void shouldListAllUsersFromSpecifiedPageInPagination() {
        given()
                .when()
                .get("/users?page=2")
                .then()
                .statusCode(HttpStatus.SC_OK)
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

    @Test
    public void shouldListSingleUser() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("data.id", equalTo(2))
                .body("data.email", equalTo("janet.weaver@reqres.in"))
                .body("data.first_name", equalTo("Janet"))
                .body("data.last_name", equalTo("Weaver"))
                .body("data.avatar", equalTo("https://reqres.in/img/faces/2-image.jpg"));
    }

}
