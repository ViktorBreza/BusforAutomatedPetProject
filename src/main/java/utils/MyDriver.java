package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class MyDriver {
    private static final Logger logger = LoggerFactory.getLogger(MyDriver.class); // SLF4J Logger

    private static volatile MyDriver instance = null;
    private final WebDriver driver;
    private final WebDriverWait wait;

    private static final Map<String, Supplier<WebDriver>> driverMap = new HashMap<>();

    static {
        driverMap.put("chrome", () -> {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        });
        driverMap.put("firefox", () -> {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();
        });
    }

    private MyDriver(String browser) {
        try {
            logger.info("Initializing WebDriver for browser: {}", browser);
            Supplier<WebDriver> driverSupplier = driverMap.get(browser.toLowerCase());
            if (driverSupplier != null) {
                this.driver = driverSupplier.get();
            } else {
                throw new IllegalArgumentException("Invalid browser name: " + browser);
            }
        } catch (Exception e) {
            logger.error("Failed to initialize WebDriver for browser: {}", browser, e);
            throw new RuntimeException("Failed to initialize WebDriver for browser: " + browser, e);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Add shutdown hook to ensure driver is closed
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (driver != null) {
                logger.info("Shutting down WebDriver");
                driver.quit();
            }
        }));
    }

    public static MyDriver getInstance(String browser) {
        if (instance == null) {
            synchronized (MyDriver.class) {
                if (instance == null) {
                    logger.info("Creating new MyDriver instance");
                    instance = new MyDriver(browser);
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void quit() {
        if (driver != null) {
            logger.info("Quitting WebDriver instance");
            driver.quit();
            instance = null;
        }
    }

    public void restartDriver(String browser) {
        logger.info("Restarting WebDriver for browser: {}", browser);
        if (driver != null) {
            driver.quit();
        }
        instance = new MyDriver(browser);
    }
}
