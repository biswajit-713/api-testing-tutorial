package com.reqres.demo.getexample;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

@Test
public class GetUserResource {
    @Test
    public void shouldListAllUsersFromSpecifiedPageInPagination() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200);
    }
}
