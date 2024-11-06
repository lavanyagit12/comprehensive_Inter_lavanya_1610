import pytest
from selenium import webdriver
from selenium.webdriver.common.by import By 
from selenium.webdriver.support.ui import WebDriverWait
from selenium.webdriver.support import expected_conditions as EC
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.chrome.service import Service
from selenium.webdriver.chrome.options import Options
from selenium.webdriver.common.action_chains import ActionChains
from webdriver_manager.chrome import ChromeDriverManager
from datetime import datetime, timedelta
import time

@pytest.fixture
def driver():
   # Initialize Chrome driver with webdriver-manager
    chrome_options = Options()
    chrome_options.add_argument("--start-maximized")
    service = Service(ChromeDriverManager().install()) 
    driver = webdriver.Chrome(service=service, options=chrome_options)
    yield driver
    #driver.quit()

def test_flight_booking(driver):
    # Open the MakeMyTrip website
    driver.get("https://www.makemytrip.com/")
    
    
    # Close the initial login popup if it appears
    WebDriverWait(driver, 5).until(EC.element_to_be_clickable((By.XPATH, "//span[@class='commonModal__close']"))).click()
    
    
    # Click on the Flights tab
    driver.find_element(By.XPATH, "//li[@data-cy='menu_Flights']").click()
    
    # Select ROUND TRIP option
    round_trip = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, "//li[@data-cy='roundTrip']")))
    round_trip.click()

    # Enter FROM location as HYD
    from_location = WebDriverWait(driver, 1).until(EC.element_to_be_clickable((By.ID, "fromCity")))
    from_location.click()
    from_location.send_keys("HYD")    
    #Wait and click on the dropdown result for Hyderabad
    WebDriverWait(driver, 10).until(EC.element_to_be_clickable((By.XPATH, "//p[contains(text(), 'Hyderabad, India')]"))).click()
    

    # Enter TO location as MAA
    to_location = WebDriverWait(driver, 1).until(EC.element_to_be_clickable((By.ID, "toCity")))
    to_location.click()
    to_location.send_keys("MAA")
    WebDriverWait(driver, 5).until(EC.presence_of_element_located((By.XPATH, "//p[contains(text(), 'Chennai, India')]"))).click()

    # Select DEPARTURE and Return date 
    def select_dates(driver, departure_days_from_today, return_days_from_today):
        # Calculate the from and to dates
        from_date = datetime.now() + timedelta(days=departure_days_from_today)
        to_date = datetime.now() + timedelta(days=return_days_from_today)

        # Format the dates in "EEE MMM dd yyyy"
        from_date_formatted = from_date.strftime("%a %b %d %Y")
        to_date_formatted = to_date.strftime("%a %b %d %Y")

        # Select the "From" date
        from_date_element = WebDriverWait(driver, 10).until(
        EC.element_to_be_clickable((By.XPATH, f"//div[@aria-label='{from_date_formatted}']"))).click()

        # Select the "To" date
        to_date_element = WebDriverWait(driver, 10).until(EC.element_to_be_clickable((
        By.XPATH, f"//div[@aria-label='{to_date_formatted}']"))).click()
    
    # Click on the Search button
    search_button = driver.find_element(By.XPATH, "//a[text()='Search']").click()

    # Verify that the search results page is displayed
    def verify_search_results(driver, to_location):
        # Get the current URL
        current_url = driver.current_url
    
        # Check if the expected location is in the current URL
        if to_location in current_url:
            print("Search page displayed successfully.")
        else:
            print("Search page not displayed.") 
