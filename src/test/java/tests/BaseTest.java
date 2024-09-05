package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MyDriver;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    protected MyDriver myDriver;
    protected static final Logger logger = LoggerFactory.getLogger(BaseTest.class); // SLF4J Logger

    @Parameters({"browser"})
    @BeforeClass
    public void setUp(@Optional("chrome") String browser) {
        try {
            System.setProperty("webdriver.http.factory", "jdk-http-client");

            // Initialize WebDriver
            myDriver = MyDriver.getInstance(browser);
            logger.info("Browser launched: {}", browser);

            // Perform login action
            // TODO uncomment after fixing the login issue
            // login();
        } catch (Exception e) {
            logger.error("Error during setup: {}", e.getMessage());
            throw new RuntimeException("Failed to initialize the test", e);
        }
    }

    @AfterClass
    public void tearDown() {
        if (myDriver != null) {
            myDriver.quit();
            logger.info("Browser closed.");
        }
    }

    protected void login() {
        WebDriver driver = myDriver.getDriver();
        WebDriverWait wait = myDriver.getWait();

        try {
            driver.get("https://busfor.ua/");
            logger.info("Navigated to busfor.ua");

            // Click on 'Особистий кабінет'
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Особистий кабінет')]")));
            loginButton.click();
            logger.info("Clicked on 'Особистий кабінет'");

            // Click on the phone input field
            WebElement phoneInputField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='auth']")));
            phoneInputField.click();
            logger.info("Clicked on phone input field");

            // Click on the 'Send code' button
            WebElement sendCodeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@role='button']")));
            sendCodeButton.click();
            logger.info("Clicked on 'Send code' button");

        } catch (Exception e) {
            logger.error("Error during login: {}", e.getMessage());
            throw new RuntimeException("Login failed", e);
        }
    }
}
