package intertrustAutomation.stepDefinitions;

import intertrustAutomation.utils.PageElements;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;


public class StepDefinitionsUI {
    
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final String url = "https://www.visualcrossing.com";
    private static final String chromeDriver = "http://browser:4444/wd/hub";
    PageElements pageElements;
    private static String searchedCity;


    @Before("@UIFeatures")
    public static void setWebDriver() throws MalformedURLException {
        // Setting up chrome driver before running the scenario in UIFeatures
        ChromeOptions options = new ChromeOptions();
        driver = new RemoteWebDriver(new URL(chromeDriver), options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(3L));
    }

    @After("@UIFeatures")
    public void tearDown() {
        // Closing the browser after the test is completed
        driver.quit();
    }

    @Given("Open VisualCrossing URL")
    public void openPageURL() {
        // Opening the page url and maximizing the window
        driver.get(url);
        driver.manage().window().maximize();

        // Wait until the visibility of Accept button in the cookies pop-up
        pageElements = new PageElements(driver);
        wait.until(ExpectedConditions.visibilityOf(pageElements.acceptCookiesBtn));
        pageElements.acceptCookiesBtn.click();
    }

    @And("Select the “Weather Data” menu")
    public void selectTheWeatherDataMenu() {
        pageElements = new PageElements(driver);
        // Wait until the visibility of the Weather Data menu and click
        wait.until(ExpectedConditions.visibilityOf(pageElements.weatherDataMenu));
        pageElements.weatherDataMenu.click();
    }

    @Then("Enter your current city {string} in the text field and press “Search” button")
    public void searchWeatherForecastForCity(String city) {
        pageElements = new PageElements(driver);

        searchedCity = city;
        // Wait until the visibility of city text field
        wait.until(ExpectedConditions.visibilityOf(pageElements.cityTextField));
        pageElements.cityTextField.sendKeys(city);
        pageElements.searchBtn.click();
    }

    @And("Verify that weather forecast is shown for the entered city")
    public void verifyThatWeatherForecastIsShownForTheCity() {
        pageElements = new PageElements(driver);

        // Wait until the visibility of all elements
        wait.until(ExpectedConditions.visibilityOfAllElements(pageElements.locationDropdownMenuButton, pageElements.maxTemp, pageElements.minTemp));

        // Verify that weather forecast is displayed for the searched city
        Assert.assertEquals(searchedCity, pageElements.locationDropdownMenuButton.getText());

        // Verify that min and max temperature for the city are displayed
        Assert.assertTrue(pageElements.maxTemp.isDisplayed());
        Assert.assertTrue(pageElements.minTemp.isDisplayed());

        // Verify that below information is given for the forecast
        Assert.assertTrue(driver.getPageSource().contains("Temperature"));
        Assert.assertTrue(driver.getPageSource().contains("Feels like"));
        Assert.assertTrue(driver.getPageSource().contains("Normal"));
        Assert.assertTrue(driver.getPageSource().contains("Maximum"));
        Assert.assertTrue(driver.getPageSource().contains("Minimum"));
    }
}
