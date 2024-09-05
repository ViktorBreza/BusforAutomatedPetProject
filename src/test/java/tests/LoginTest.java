package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(enabled = false)
    public void testSuccessfulLogin() {
        // Call the login method from BaseTest, which performs the login actions
        login();

        // Verify that the "My Profile" element appears after login
        WebElement accountElement = myDriver.getDriver().findElement(By.xpath("//span[contains(text(),'Мій профіль')]"));

        // Perform a check to ensure the "My Profile" element is present on the page
        Assert.assertTrue(accountElement.isDisplayed(), "Login failed or profile element not found.");

        // Log that the login test passed successfully
        logger.info("Login test passed, profile element found.");
    }
}
