import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import utils.MyDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SearchTicketApplication extends Application {

    private static final Logger logger = LoggerFactory.getLogger(SearchTicketApplication.class);

    private TextField departureCityField;
    private TextField destinationCityField;
    private TextField dateField;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bus Search Application");

        // Create UI components
        Label departureCityLabel = new Label("Departure City:");
        Label destinationCityLabel = new Label("Destination City:");
        Label dateLabel = new Label("Date:");

        departureCityField = new TextField();
        destinationCityField = new TextField();
        dateField = new TextField();

        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> performSearch());

        // Layout
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(20));

        grid.add(departureCityLabel, 0, 0);
        grid.add(departureCityField, 1, 0);
        grid.add(destinationCityLabel, 0, 1);
        grid.add(destinationCityField, 1, 1);
        grid.add(dateLabel, 0, 2);
        grid.add(dateField, 1, 2);
        grid.add(searchButton, 1, 3);

        // Set the scene and show the stage
        Scene scene = new Scene(grid, 400, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void performSearch() {
        String departureCity = departureCityField.getText();
        String destinationCity = destinationCityField.getText();
        String date = dateField.getText();

        // Initialize WebDriver
        MyDriver myDriver = MyDriver.getInstance("chrome"); // Or "firefox" as needed
        WebDriver driver = myDriver.getDriver();
        MainPage mainPage = new MainPage(myDriver);

        try {
            // Open the webpage
            driver.get("https://example.com"); // Replace with the actual URL
            logger.info("Navigated to the webpage");

            // Perform search
            mainPage.enterAndSelectDepartureCity(departureCity);
            mainPage.enterDestinationCity(destinationCity);
            mainPage.selectDate(date);
            mainPage.clickFindTickets();

            logger.info("Search performed with the following details:");
            logger.info("Departure City: {}", departureCity);
            logger.info("Destination City: {}", destinationCity);
            logger.info("Date: {}", date);

            // Add additional logic to verify results if needed

        } catch (Exception e) {
            logger.error("Error during search application execution: {}", e.getMessage());
        } finally {
            // Quit WebDriver
            myDriver.quit();
        }
    }
}
