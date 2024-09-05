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

    /**
     * Constructs a By locator for the bus schedule title element on the page.
     *
     * The title is expected to contain a format like:
     * "Розклад автобусів {departureCity} — {destinationCity} на {dayNumber}"
     * where:
     * - {departureCity} is the city from which the bus departs,
     * - {destinationCity} is the city to which the bus arrives,
     * - {dayNumber} represents the date of the schedule.
     *
     * This method uses the provided parameters to dynamically generate the XPath
     * expression to locate the title element based on the cities and date specified.
     *
     * @param departureCity The city from which the bus departs (e.g., "Варшава").
     * @param destinationCity The city to which the bus arrives (e.g., "Рівне").
     * @param dayNumber The date for the bus schedule (e.g., "13 вересня").
     * @return A By object representing the XPath locator for the bus schedule title element.
     */
    public By busScheduleTitleLocator(String departureCity, String destinationCity, String dayNumber) {
        String xpathExpression = String.format("//h4[contains(text(),'Розклад автобусів %s — %s на %s')]", departureCity, destinationCity, dayNumber);
        return By.xpath(xpathExpression);
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
