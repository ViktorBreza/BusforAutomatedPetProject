package tests;

import org.testng.annotations.Test;
import pages.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchRoutesTest extends BaseTest {

    // Test Data Constants
    private static final String DEPARTURE_CITY = "Рівне";
    private static final String DESTINATION_CITY = "Одеса";
    private static final String DATE = "17";

    @Test
    public void searchRoutes() {

        MainPage mainPage = new MainPage(myDriver);

        // Use methods of MainPage with constants
        mainPage.enterAndSelectDepartureCity(DEPARTURE_CITY);
        mainPage.enterDestinationCity(DESTINATION_CITY);
        mainPage.selectDate(DATE);
        mainPage.clickFindTickets();

        // Get the actual text from the bus schedule title element
        String actualTitleText = mainPage.getElementText(mainPage.busScheduleTitleLocator(DEPARTURE_CITY, DESTINATION_CITY, DATE));

        // Assert that the actual title text contains the expected text
        assertThat(actualTitleText).contains(String.format("Розклад автобусів %s — %s на %s", DEPARTURE_CITY, DESTINATION_CITY, DATE));
    }
}
