package intertrustAutomation.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;


import java.util.ArrayList;
import java.util.Arrays;

import static io.restassured.RestAssured.given;


public class StepDefinitionsAPI {
    private final String baseUri = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
    private final String authToken = System.getenv("AUTH_TOKEN");
    private Response response;
    private static String requestedCity;

    @When("Send GET request to the VisualCrossing API endpoint for {string}")
    public void sendGetRequest(String city) {
        RestAssured.baseURI = baseUri;

        response = given()
                .contentType(ContentType.JSON)
                .when()
                .get(baseUri + city + "?unitGroup=metric&key=" + authToken + "&contentType=json")
                .then()
                .extract().response();

        requestedCity = city;
    }

    @And("Verify status code is {int}")
    public void verifyStatusCode(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @Then("Verify that forecast is given for the correct city")
    public void verifyThatCityIsCorrect() {
        Assert.assertEquals(requestedCity, response.path("address"));
    }

    @And("Verify that timezone is {string}")
    public void verifyThatTimezoneIsCorrect(String timezone) {
        Assert.assertEquals(timezone, response.path("timezone"));
    }

    @And("Verify that forecast is given for {int} days")
    public void verifyThatForecastIsGivenForGivenDays(int forecastedDays) {
        ArrayList<String> days = new ArrayList<>(Arrays.asList(response.path("days")));
        Assert.assertEquals(forecastedDays, days.size());
    }
}
