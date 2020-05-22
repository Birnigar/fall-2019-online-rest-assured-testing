package com.automation.tests.day3;

import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class ORDSTestDay3 {

    @BeforeAll
    public static void setup(){
        baseURI="http://54.224.118.38:1000/ords/hr";

    }
    /**
     * given path parameter is "/regions/{id}"
     * when user makes get request
     * and region id is equals to 1
     * then assert that status code is 200
     * and assert that region name is Europe
     */

    @Test
    public void verifyFirstRegion(){

                given().
                    pathParam("id",1).
                when().
                    get("/regions/{id}").prettyPeek().
                then().
                    assertThat().statusCode(200).body("region_name",is("Europe")).
                        body("region_id",equalTo(1)).
                        time(lessThan(5L), TimeUnit.SECONDS);//verify that response time is less than 5 seconds
    }

}
