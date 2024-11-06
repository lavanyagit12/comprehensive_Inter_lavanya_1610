package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Inter.AssignmentPS7.flight.test.roundTripFlightBooking;
import io.cucumber.java.en.*;


public class flightBookingSteps {
    WebDriver driver;
    WebDriverWait wait;
    
    roundTripFlightBooking roundTrip = new roundTripFlightBooking();

    @Given("I open the browser and navigate to {string}")
    public void openBrowserAndNavigate(String url) {
       roundTrip.setupBrowser(url);  // Calls setupBrowser method
       roundTrip.modalpopup();
    }
    

    @When("I click on Flights tab and select ROUND TRIP")
    public void i_click_on_Flights_tab_and_select_ROUND_TRIP() {
        roundTrip.clickOnFlightsTab();  // Calls clickOnFlightsTab method
        roundTrip.selectRoundTrip();    // Calls selectRoundTrip method
    }
    
    @Given("I enter source location as HYD")
    public void i_enter_source_location_as_HYD() {
    	roundTrip.sourceLocation("fromLocation");  // Calling Source Location	
    }
    
    @Given("I enter destination location as MAA")
    public void i_enter_destination_location_as_MAA() {
    	roundTrip.destinationLocation("toLocation");   // Calling Destination Location
    }

    @When("I select Departure and Return Dates as {string} and {string}")
    public void i_select_Departure_and_Return_Dates_as(String departure, String returnDate) {
    	int departureDaysFromToday = 0;
        int returnDaysFromToday = 0;

        // Parse the input to calculate days from today
        if (departure.equalsIgnoreCase("Today's date")) {
            departureDaysFromToday = 0;
        }
        if (returnDate.startsWith("Today's date +")) {
            returnDaysFromToday = Integer.parseInt(returnDate.replace("Today's date + 3", "").trim());
        }
    	
        roundTrip.selectDates(departureDaysFromToday, returnDaysFromToday);  // Calls selectDates method
    }

    @When("I click on the Search button")
    public void i_click_on_the_Search_button() {
        roundTrip.clickSearchButton();  // Calls clickSearchButton method
        
    }

    @Then("I verify the Search page is displayed with {string}")
    public void i_verify_the_Search_page_is_displayed(String toLocation) {
        roundTrip.verifySearchPageIsDisplayed(toLocation);   // Calls SearchPageDisplayed method
        roundTrip.driver.quit();
    }
    
}
