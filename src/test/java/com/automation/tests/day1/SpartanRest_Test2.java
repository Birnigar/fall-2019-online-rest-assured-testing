package com.automation.tests.day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class SpartanRest_Test2 {
    @BeforeAll
    public static void setUp(){
        baseURI="http://54.196.47.224";
        port=8000;
        basePath="api";
        //above will generate a BASE REQUEST URL of http://54.196.47.224:/8000/api
        //8000 is the port for this particular
    }

    @Test
    public void test1(){
        Response response=
        given().
                accept(ContentType.JSON).
        when().
                get("/spartans");

    }







}
