package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.MyDriver;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    /**
     * Constructor to initialize WebDriver and WebDriverWait.
     *
     * @param myDriver MyDriver instance that provides WebDriver and WebDriverWait.
     */
    public BasePage(MyDriver myDriver) {
        this.driver = myDriver.getDriver();
        this.wait = myDriver.getWait();
    }

    /**
     * Waits for an element to be visible and returns it.
     *
     * @param locator By locator for the element.
     * @return WebElement that is visible.
     */
    protected WebElement waitForElementToBeVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /**
     * Waits for an element to be clickable and returns it.
     *
     * @param locator By locator for the element.
     * @return WebElement that is clickable.
     */
    protected WebElement waitForElementToBeClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    /**
     * Clicks on an element specified by its locator.
     *
     * @param locator By locator for the element.
     */
    protected void clickElement(By locator) {
        WebElement element = waitForElementToBeClickable(locator);
        element.click();
    }

    /**
     * Gets the text of an element specified by its locator.
     *
     * @param locator By locator for the element.
     * @return The text of the element.
     */
    public String getElementText(By locator) {
        WebElement element = waitForElementToBeVisible(locator);
        return element.getText();
    }
}
