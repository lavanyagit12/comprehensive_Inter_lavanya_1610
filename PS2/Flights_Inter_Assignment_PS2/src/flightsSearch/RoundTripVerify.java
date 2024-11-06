package flightsSearch;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class RoundTripVerify {

    private WebDriver driver;
    
    // Main method to execute the test
    public static void main(String[] args) {
        RoundTripVerify trip = new RoundTripVerify();

        trip.Launchwebsite();
        trip.modalpopup();
        trip.clickFlightsTab();
        trip.selectRoundTrip();
        trip.sourceLocation("HYD");
        trip.destinationLocation("MAA");
        trip.selectDates(0, 3); // Set departure date as today and return 3 days from today
        trip.clickSearchButton();
        trip.verifySearchResults("MAA");

        //trip.driver.quit();
    }


    // Constructor to initialize WebDriver and launch the browser
    public RoundTripVerify() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    // Method to open the MakeMyTrip website
    public void Launchwebsite() {
        driver.get("https://www.makemytrip.com/");
    }

    // Method to close any pop-ups
    public void modalpopup() {
      
            WebElement closeLoginPopup = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
            closeLoginPopup.click();
       
    }

    // Method to click on the Flights tab
    public void clickFlightsTab() {
        WebElement flightsTab = driver.findElement(By.xpath("//span[text()='Flights']"));
        flightsTab.click();
    }

    // Method to select Round Trip
    public void selectRoundTrip() {
        WebElement roundTripRadioButton = driver.findElement(By.xpath("//li[text()='Round Trip']"));
        roundTripRadioButton.click();
    }

    // Method to enter the "From" location
    public void sourceLocation(String fromLocation) {
        WebElement fromCity = driver.findElement(By.id("fromCity"));
        fromCity.click();
        WebElement fromSearchBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromSearchBox.sendKeys(fromLocation);
        WebElement selectFromCity = driver.findElement(By.xpath("//p[contains(text(), 'Hyderabad, India')]"));
        selectFromCity.click();
    }

    // Method to enter the "To" location
    public void destinationLocation(String toLocation) {
        WebElement toCity = driver.findElement(By.id("toCity"));
        String tocitysearchtext = "MAA";
        toCity.click();
        WebElement toSearchBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toSearchBox.sendKeys(tocitysearchtext);
        WebElement selectToCity = driver.findElement(By.xpath("//p[contains(text(), 'Chennai, India')]"));
        selectToCity.click();

    }

    // Method to select the "From" and "To" dates
    public void selectDates(int departureDaysFromToday, int returnDaysFromToday) {
        LocalDate fromDate = LocalDate.now().plusDays(departureDaysFromToday);
        LocalDate toDate = LocalDate.now().plusDays(returnDaysFromToday);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        String fromDateFormatted = fromDate.format(formatter);
        String toDateFormatted = toDate.format(formatter);

        WebElement fromDateElement = driver.findElement(By.xpath("//div[@aria-label='" + fromDateFormatted + "']"));
        fromDateElement.click();

        WebElement toDateElement = driver.findElement(By.xpath("//div[@aria-label='" + toDateFormatted + "']"));
        toDateElement.click();
    }

    // Method to click the Search button
    public void clickSearchButton() {
        WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
        searchButton.click();
    }

    // Method to verify the search results page
    public void verifySearchResults(String expectedLocation) {
        if (driver.getCurrentUrl().contains(expectedLocation)) {
            System.out.println("Search page displayed successfully.");
        } else {
            System.out.println("Search page not displayed.");
        }
    }

}
