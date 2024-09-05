package pages;

import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.MyDriver;

public class MainPage extends BasePage {

    // Logger for MainPage
    private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

    // Locators
    private final By departureCityField = By.xpath("//input[@id='from']");
    private final By destinationCityField = By.xpath("//input[@id='to']");
    private final By dateField = By.xpath("//input[@id='on']");
    private final By findTicketsButton = By.xpath("//button[@id='submit']");

    /**
     * @param city The name of the city to use in the locator.
     * @return A By locator for the specified city.
     */
    private By getSearchedCityLocator(String city) {
        return By.xpath("//span[@class='selected-bold'][contains(text(),'" + city + "')]");
    }

    /**
     * @param dayNumber The day number to locate.
     * @return A By locator for the specified day.
     */
    private By getDayLocator(String dayNumber) {
        return By.xpath("//span[normalize-space()='" + dayNumber + "']");
    }

    // Constructor
    public MainPage(MyDriver myDriver) {
        super(myDriver); // Call the constructor of BasePage
        logger.info("MainPage initialized with MyDriver.");
    }

    // Methods to interact with the page
    public void enterAndSelectDepartureCity(String departureCity) {
        logger.info("Entering departure city: {}", departureCity);
        waitForElementToBeClickable(departureCityField).sendKeys(departureCity);
        logger.info("Selecting departure city: {}", departureCity);
        clickElement(getSearchedCityLocator(departureCity));
        logger.info("Departure city selected: {}", departureCity);
    }

    public void enterDestinationCity(String destinationCity) {
        logger.info("Entering destination city: {}", destinationCity);
        waitForElementToBeClickable(destinationCityField).sendKeys(destinationCity);
        logger.info("Selecting destination city: {}", destinationCity);
        clickElement(getSearchedCityLocator(destinationCity));
        logger.info("Destination city selected: {}", destinationCity);
    }

    public void selectDate(String dayNumber) {
        logger.info("Clicking on date field.");
        clickElement(dateField);
        logger.info("Selecting date: {}", dayNumber);
        clickElement(getDayLocator(dayNumber));
        logger.info("Date selected: {}", dayNumber);
    }

    public void clickFindTickets() {
        logger.info("Clicking on 'Find Tickets' button.");
        clickElement(findTicketsButton);
        logger.info("'Find Tickets' button clicked.");
    }
}
