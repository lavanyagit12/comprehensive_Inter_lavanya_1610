Feature: Flight Booking on MakeMyTrip

  Scenario: Search for a round trip flight from Hyderabad to Chennai
    Given I open the browser and navigate to "https://www.makemytrip.com/"
    And I click on Flights tab and select ROUND TRIP
    And I enter source location as HYD
    And I enter destination location as MAA
    And I select Departure and Return Dates as "Todays Date" and "Todays date + 3"
    When I click on the Search button
    Then I verify the Search page is displayed with "MAA"
