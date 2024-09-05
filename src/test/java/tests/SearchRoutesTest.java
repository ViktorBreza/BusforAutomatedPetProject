package tests;

import org.testng.annotations.Test;
import pages.MainPage;

public class SearchRoutesTest extends BaseTest {

    @Test
    public void searchRoutes() {

        MainPage mainPage = new MainPage(myDriver);

        // Use methods of MainPage
        mainPage.enterAndSelectDepartureCity("Рівне");
        mainPage.enterDestinationCity("Одеса");
        mainPage.selectDate("17");
        mainPage.clickFindTickets();

        // Add assertions or additional test steps here
        System.out.println("");
    }
}
