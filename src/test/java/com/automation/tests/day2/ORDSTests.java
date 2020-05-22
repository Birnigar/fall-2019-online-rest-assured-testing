package com.automation.tests.day2;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class ORDSTests {
    String BASE_URL = "http://3.90.112.152:1000/ords/hr";

    @Test
    @DisplayName("Get list of all employees")
    public void getAllEmployees() {
        //response can be in the response object
        //prettyPeek() - method prints response info in nice format
        //response contains body, header and status line
        //body(payload)- contains content that we requested from the web service
        //header - contains meta data
       Response response= given().
                baseUri(BASE_URL).
                when().
                get("/employees").prettyPeek();//displays entire response information

        /**
         * RestAssured request has similar structure  to BDD scenarios
         * give() - used for request setup and authentication
         * when() - to specify type of HTTP request : get , put, post, delete, patch, head, etc.
         * then() - to verify response , perform assertion
         */

    }
    @Test
    @DisplayName("Get employee under specific ID")
    public void getOneEmployee(){
        //in URL we can specify path and query parameters
        //path parameters are used to retrieve specific resource: for example 1 employee not all of them
        //{id} - path variable , that will be replace with a value after coma
        //after when() we specify HTTP request type/method/verb
        //The parameters. E.g. if path is "book/{hotelId}/{roomNumber}" you can do <code>get ("book/{hotelId}/{roomNumber}","Hotel R Us ",22);</code>
        Response response=given().baseUri(BASE_URL).when().get("/employees/{id}",100).prettyPeek();

        //how we verify response? - use assertion
        //200s always expected status code after Get request

        response.then().statusCode(200);//to verify that status is 200

    }
    @Test
    @DisplayName("Get all countries and the verify")
    public void getAllCounty() {

                given().
                        baseUri(BASE_URL).
                when().
                        get("/countries").prettyPeek().
                then().
                        statusCode(200).statusLine("OK");

    }
    @Test
    @DisplayName("Get all job and the verify")
    public void getAllJob() {

        given().
                baseUri(BASE_URL).
                when().
                get("/jobs").prettyPeek().
                then().
                statusCode(200).statusLine("OK");

    }
}
