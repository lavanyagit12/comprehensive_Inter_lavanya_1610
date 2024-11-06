package Inter.AssignmentPS7.flight.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import io.github.bonigarcia.wdm.WebDriverManager;

public class roundTripFlightBooking {
    public WebDriver driver;
    WebDriverWait wait;

    // Method to set up and open the browser with the given URL
    public void setupBrowser(String url) {
    	WebDriverManager.chromedriver().setup();  
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Method to close any pop-ups
    public void modalpopup() {
      
            WebElement closeLoginPopup = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='commonModal__close']")));
            closeLoginPopup.click();
    }

    // Method to click on the "Flights" tab
    public void clickOnFlightsTab() {
        WebElement flightsTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Flights']")));
        flightsTab.click();
    }

    // Method to select the round-trip option
    public void selectRoundTrip() {
        WebElement roundTripOption = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//li[text()='Round Trip']")));
        roundTripOption.click();
    }

    // Method to enter "From" and "To" locations
    
    public void sourceLocation(String fromLocation) {
        WebElement fromCity = wait.until(ExpectedConditions.elementToBeClickable(By.id("fromCity")));
        String fromcitysearchtext = "HYD";
        fromCity.click();
        WebElement fromSearchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='From']")));
        fromSearchBox.sendKeys(fromcitysearchtext);
        WebElement selectFromCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Hyderabad, India')]")));
        selectFromCity.click();
    }
    
    public void destinationLocation(String toLocation) {
        WebElement toCity = wait.until(ExpectedConditions.elementToBeClickable(By.id("toCity")));
        String tocitysearchtext = "MAA";
        toCity.click();
        WebElement toSearchBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='To']")));
        toSearchBox.sendKeys(tocitysearchtext);
        WebElement selectToCity = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[contains(text(), 'Chennai, India')]")));
        selectToCity.click();
    }       

    // Method to select departure and return dates
    public void selectDates(int departureDaysFromToday, int returnDaysFromToday) {
        
        LocalDate fromDate = LocalDate.now().plusDays(departureDaysFromToday);
        LocalDate toDate = LocalDate.now().plusDays(3);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");
        String fromDateFormatted = fromDate.format(formatter);
        String toDateFormatted = toDate.format(formatter);

        WebElement fromDateElement = wait.until(ExpectedConditions.elementToBeClickable
        		(By.xpath("//div[@aria-label='" + fromDateFormatted + "']")));
        fromDateElement.click();

        WebElement toDateElement = wait.until(ExpectedConditions.elementToBeClickable
        		(By.xpath("//div[@aria-label='" + toDateFormatted + "']")));
        toDateElement.click();
    }
    
    // Method to click on the "Search" button
    public void clickSearchButton() {
    	WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Search']")));
        searchButton.click();
    }

    // Method to verify the search results page is displayed
    public void verifySearchPageIsDisplayed(String expectedLocation) {
        if (driver.getCurrentUrl().contains(expectedLocation)) {
            System.out.println("Search page displayed successfully.");
        } else {
            System.out.println("Search page not displayed.");
        }
    }
    

}
  