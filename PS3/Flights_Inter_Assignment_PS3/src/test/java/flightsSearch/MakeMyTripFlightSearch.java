package flightsSearch;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MakeMyTripFlightSearch {

    private WebDriver driver;

    @BeforeMethod
    // Launching Browser
    public void initializeBrowserAndNavigateToWebsite() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.makemytrip.com/");
    }

    @Test
    public void performRoundTripFlightSearch() {
        // Close any modal pop-ups
        try {
            WebElement closePopupButton = driver.findElement(By.xpath("//span[@class='commonModal__close']"));
            closePopupButton.click();
        } catch (Exception e) {
            System.out.println("No modal pop-ups found on the homepage.");
        }

        // Select the Flights tab
        WebElement flightsTabButton = driver.findElement(By.xpath("//span[text()='Flights']"));
        flightsTabButton.click();

        // Choose Round Trip option
        WebElement roundTripOptionButton = driver.findElement(By.xpath("//li[text()='Round Trip']"));
        roundTripOptionButton.click();

        // Set "From" location as Hyderabad
        WebElement fromLocationInput = driver.findElement(By.id("fromCity"));
        fromLocationInput.click();
        WebElement fromSearchBox = driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromSearchBox.sendKeys("HYD");
        WebElement hyderabadOption = driver.findElement(By.xpath("//p[contains(text(), 'Hyderabad, India')]"));
        hyderabadOption.click();

        // Set "To" location as Chennai
        WebElement toLocationInput = driver.findElement(By.id("toCity"));
        toLocationInput.click();
        WebElement toSearchBox = driver.findElement(By.xpath("//input[@placeholder='To']"));
        toSearchBox.sendKeys("MAA");
        WebElement chennaiOption = driver.findElement(By.xpath("//p[contains(text(), 'Chennai, India')]"));
        chennaiOption.click();

        // Select departure and return dates
        LocalDate departureDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now().plusDays(3);
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy");

        String formattedDepartureDate = departureDate.format(dateFormatter);
        String formattedReturnDate = returnDate.format(dateFormatter);

        WebElement departureDateElement = driver.findElement(By.xpath("//div[@aria-label='" + formattedDepartureDate + "']"));
        departureDateElement.click();

        WebElement returnDateElement = driver.findElement(By.xpath("//div[@aria-label='" + formattedReturnDate + "']"));
        returnDateElement.click();

        // Click the Search button to submit
        WebElement searchFlightsButton = driver.findElement(By.xpath("//a[text()='Search']"));
        searchFlightsButton.click();

        // Verify the results page
        String expectedLocation = "MAA";
        if (driver.getCurrentUrl().contains(expectedLocation)) {
            System.out.println("Flight search results page loaded successfully.");
        } else {
            System.out.println("Flight search results page did not load as expected.");
        }
    }

    @AfterMethod
    public void closeBrowserAfterTest() {
        if (driver != null) {
            driver.quit();
        }
    }
}
