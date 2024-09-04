package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyDriver {
    private static MyDriver instance = null;
    private final WebDriver driver;

    // Enum to represent different browsers
    public enum BrowserType {
        CHROME {
            @Override
            public WebDriver createDriver() {
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            }
        },
        FIREFOX {
            @Override
            public WebDriver createDriver() {
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            }
        };

        public abstract WebDriver createDriver();
    }

    // Private constructor for setting up WebDriver
    private MyDriver(BrowserType browserType) {
        this.driver = browserType.createDriver();
        driver.manage().window().maximize(); // Maximize the browser window
    }

    // Synchronized method to get the singleton instance of MyDriver
    public static synchronized MyDriver getInstance(String browser) {
        if (instance == null) {
            instance = new MyDriver(BrowserType.valueOf(browser.toUpperCase()));
        }
        return instance;
    }

    // Method to get the WebDriver instance
    public WebDriver getDriver() {
        return driver;
    }

    // Method to quit the WebDriver and reset the singleton instance
    public void quit() {
        if (driver != null) {
            driver.quit(); // Close the browser
            instance = null; // Reset the singleton instance
        }
    }
}
