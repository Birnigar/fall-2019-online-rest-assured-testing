package com.automation.tests.day3;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;


public class ExchangeRatesAPITests {
    /**
     * given() - all input details
     * when() - submit the API
     * then() - validate the response
     */

    @BeforeAll
    public static void setup(){
        //for every single request this is a base URI
        baseURI="http://api.openrates.io";
    }


    //get latest currency rates
    @Test
    public void getLatestCurrency(){
        //q - query parameter
        //zip - another query parameter
        //with rest assured, we provide query parameters into given() part.
        //give() - request preparation
        //you can specify query parameters in URL explicitly
        //rest assured, will will just assemble URL for you

        Response response=given().
                             queryParam("base","USD").
                        when().
                             get("latest").prettyPeek();
        //verify that request to the endpoint was successful
        Headers headers=response.getHeaders();

        String contentType=headers.getValue("Content_Type");
        System.out.println(contentType);
        response.then().assertThat().statusCode(200);

        response.then().assertThat().body("base",is("USD"));

        //let's verify that response contains today date
        //this line return todays date in the required format yyyy-MM-dd
       String date= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        response.then().assertThat().body("date",containsString(date));
        //is the same as equals



    }

    @Test
    public void getHistoryOfRates(){
        Response response=given().queryParam("base","USD").
                when().
                        get("2008-01-02").prettyPeek();

        Headers headers=response.getHeaders();//response header
        System.out.println(headers);


        response.then().assertThat().statusCode(200).and().body("date",is("2008-01-02")).and().
                body("rates.USD", is(1.0f));
        //adn() doesn't have any functional role ,it is just syntax sugar
        //we can cain validation
        //how we can retrieve


        //rates - it's a object
        //all currencies are like instance variables
        //to get any distance variable (property), objectName.propertyName
        float actual=response.jsonPath().get("rates.EUR");
        System.out.println(actual);
        assertEquals(1.0,actual);
        /**
         *  Get a JsonPath view of the response body. This will let you use the JsonPath syntax to get values from the response.
         *      * Example:
         *      * <p>
         *      * Assume that the GET request (to <tt>http://localhost:8080/lotto</tt>) returns JSON as:
         *      * <pre>
         *      * {
         *      * "lotto":{
         *      *   "lottoId":5,
         *      *   "winning-numbers":[2,45,34,23,7,5,3],
         *      *   "winners":[{
         *      *     "winnerId":23,
         *      *     "numbers":[2,45,34,23,3,5]
         *      *   },{
         *      *     "winnerId":54,
         *      *     "numbers":[52,3,12,11,18,22]
         *      *   }]
         *      *  }
         *      * }
         *      * </pre>
         *      * </p>
         *      * You can the make the request and get the winner id's by using JsonPath:
         *      * <pre>
         *      * List<Integer> winnerIds = get("/lotto").jsonPath().getList("lotto.winnders.winnerId");
         *      * </pre>
         *
         */



    }
}
