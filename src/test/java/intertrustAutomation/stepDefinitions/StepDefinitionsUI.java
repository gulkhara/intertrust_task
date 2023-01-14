package intertrustAutomation.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

public class stepDefinitionsUI {
    
    public static WebDriver driver;
    public static String url = "https://www.visualcrossing.com";

    //public static String chromeDriver = "http://browser:4444/wd/hub";


    @Before("@UIFeatures")
    public static void setWebDriver() throws MalformedURLException {
        // Setting up chrome driver before running the scenarios in UIFeatures
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

    //    ChromeOptions options = new ChromeOptions();
    //    driver = new RemoteWebDriver(new URL(chromeDriver), options);

    }

    @After("@UIFeatures")
    public void tearDown() {
        // Closing the browser after all tests are completed
        driver.quit();
    }

    @Given("Customer opens VisualCrossing URL")
    public void openPageURL() throws InterruptedException {
        // Opening the page url and maximizing the window
        driver.get(url);
        driver.manage().window().maximize();

        // Waiting for the cookies pop-up which is inside single shadow DOM, and selecting "Accept all cookies" option
        driver.findElement(By.Xpath("//button[normalize-space()='Accept all cookies']")).click();


    }
}
