package intertrustAutomation.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

// All the elements that are used for the tests are stored in this Page Object Model (POM)

public class PageElements {
    WebDriver driver;

    public PageElements(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[normalize-space()='Accept all cookies']")
    public WebElement acceptCookiesBtn;

    @FindBy(xpath = "//a[@class='nav-link text-nowrap'][normalize-space()='Weather Data']")
    public WebElement weatherDataMenu;

    @FindBy(id = "wxlocation")
    public WebElement cityTextField;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchBtn;

    @FindBy(id = "locationDropdownMenuButton")
    public WebElement locationDropdownMenuButton;

    @FindBy(xpath = "//div[normalize-space()='Max temp']")
    public WebElement maxTemp;

    @FindBy(xpath = "//div[normalize-space()='Min temp']")
    public WebElement minTemp;
}