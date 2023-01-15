package intertrustAutomation.stepDefinitions;

import intertrustAutomation.utils.PageElements;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;


public class StepDefinitionsUI {
    
    private static WebDriver driver;
    private static String searchedCity;
    private static String url = "https://www.visualcrossing.com";
    public static String chromeDriver = "http://browser:4444/wd/hub";
    PageElements pageElements;


    @Before("@UIFeatures")
    public static void setWebDriver() throws MalformedURLException {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();

        // Setting up chrome driver before running the scenario in UIFeatures
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        driver = new RemoteWebDriver(new URL(chromeDriver), options);
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

        // Accept the cookies in the pop-up
        pageElements = new PageElements(driver);
        pageElements.acceptCookiesBtn.click();
    }

    @And("Select the “Weather Data” menu")
    public void selectTheWeatherDataMenu() {
        pageElements = new PageElements(driver);
        pageElements.weatherDataMenu.click();
    }

    @Then("Enter your current city {string} in the text field and press “Search” button")
    public void searchWeatherForecastForCity(String city) {
        pageElements = new PageElements(driver);

        searchedCity = city;
        pageElements.cityTextField.sendKeys(city);
        pageElements.searchBtn.click();
    }

    @And("Verify that weather forecast is shown for the entered city")
    public void verifyThatWeatherForecastIsShownForTheCity() {
        pageElements = new PageElements(driver);

        Assert.assertTrue(pageElements.locationDropdownMenuButton.isDisplayed());
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
