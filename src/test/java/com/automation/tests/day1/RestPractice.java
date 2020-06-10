package com.automation.tests.day1;

import static org.junit.jupiter.api.Assertions.*;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


public class RestPractice {
    @BeforeAll
    public static void setUp(){
        baseURI="http://54.196.47.224";
        port=8000;
        basePath="api";
       //above will generate a BASE REQUEST URL of http://54.196.47.224:/8000/api
        //8000 is the port for this particular
    }

    @Test
    public void test(){
        Response result= get("http://54.196.47.224:8000/api/hello");
        //this code give you status code
        System.out.println(result.statusCode());
        //this code give you the body in String format
        System.out.println(result.asString());
        System.out.println(result.getBody().asString());
        System.out.println(result.body().asString());
        //all method give same result

        System.out.println(result.getHeader("content-type"));
        assertEquals(200,result.statusCode());
        //rest assured lib provide multiple method with same meaning
        //like getHeader=header , getContentType = ContentType ,statusCode=getStatusCode
        assertEquals("Hello from Sparta",result.asString());


    }
    @Test
    public void Hello_Endpoint_Test() {
       Response result=get("/hello");
       assertEquals(200,result.statusCode());
       assertEquals("Hello from Sparta",result.asString());
    }
    @Test
    public void Hello_Endpoint_HeaderContains_Test()  {
        //header should contains date
        Response result=get("/hello");
        System.out.println(result.getHeader("Date"));

        assertNotNull(result.getHeader("Date"));
        //2.approach : use existing method
        boolean dateHeaderExists=result.getHeaders().hasHeaderWithName("Date");
        assertTrue(dateHeaderExists);

        assertEquals("17",result.getHeader("content-length"));


    }
}
