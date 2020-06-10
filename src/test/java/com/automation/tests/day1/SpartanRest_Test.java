package com.automation.tests.day1;

import io.restassured.http.ContentType;
import  io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;

public class SpartanRest_Test {
    @BeforeAll
    public static void setUp(){
        baseURI="http://54.196.47.224";
        port=8000;
        basePath="api";
        //above will generate a BASE REQUEST URL of http://54.196.47.224:/8000/api
        //8000 is the port for this particular
    }
    //Given no headers are provided
    //When user request to /Spartans
    //Then Response status code should be 200
    //and header should have content type/json
    //and header should contains Date
    @Test
    public void Spartan_All_Test(){
        Response response=get("/spartans/");
       // assertEquals(200,response.statusCode());
        //below codes are doing same exact thing
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertEquals("application/json;charset=UTF-8",response.getContentType());
        assertEquals("application/json;charset=UTF-8",response.getHeader("content-type"));

        //checking whether one header exists
        boolean hasDateHeader=response.getHeaders().hasHeaderWithName("Date");
        assertTrue(hasDateHeader);
    }

    //Given no headers are provided
    //when user send request to api/spartans/2
    //Then response status code should be 200
    //and header should have content type/json
    //Optional and json object should be 2

    @Test
    public void SingleSpartanData_Test(){
       given().
               auth().basic("admin","admin").
               when().
               get("spartans/2").prettyPeek().
               then().
               assertThat().statusCode(200).
       and().assertThat().body("name",is("Nels")).
       and().assertThat().body("id",is(2
       ));




    }

    @Test
    public void Invalid_Spartan_ID_Return_404_Test(){
        given().auth().basic("admin","admin").get("/spartans/2000").prettyPeek().
                then().
                assertThat().statusCode(404).
        and().assertThat().body("message",is("Spartan Not Found"));
    }

   @Test
    public void SingleSpartanDataWithHeader(){
        //given is RequestSpecification object hold the information
       //about request ,like header,path variable,query parameters,body

        given().auth().basic("admin","admin").accept(ContentType.JSON).when().get("/spartans/2").prettyPeek().
                then().assertThat().statusCode(200);
   }

    /**
     * Given accept header is json
     * query parameters gender Male
     * when user send request to /api/spartans/search
     * then response status code should be 200
     * and header should have content type / JSON
     *
     */
  @Test
    public  void Search_By_Providing_Query_parameter(){
      given().
          auth().basic("admin","admin").
          accept(ContentType.JSON).queryParam("gender","male").
        when().
          get("/spartans/search").prettyPeek().
        then().
              assertThat().contentType("application/json; charset=utf-8").
              and().
              assertThat().statusCode(200).
              and().
              assertThat().body("gender",is("male"));


  }
}
