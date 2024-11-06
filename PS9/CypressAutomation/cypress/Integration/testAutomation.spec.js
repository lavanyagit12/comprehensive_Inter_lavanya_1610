describe('Test Automation Demo Site', function() {

  // Load the fixture data before any tests run
  before(function() {
    cy.fixture('titledata.json').then((fixtureData) => {
      // Store the fixture data in a Cypress environment variable
      Cypress.env('pageTitles', fixtureData);
    });
  });

  // Visit the demo page before each test
  beforeEach(function() {
    cy.visit('https://automationpanda.com/2021/12/29/want-to-practice-test-automation-try-these-demo-sites/');
  });

  // Test case to verify the main page title
  it('Should verify the main page title', function() {
    const pageTitles = Cypress.env('pageTitles');
    cy.title().should('include', pageTitles.homePageTitle);
  });

  // Test case to navigate to the "Speaking" page and verify the title
  it('Should navigate to "Speaking" and verify the page title', function() {
    const pageTitles = Cypress.env('pageTitles');
    cy.contains('Speaking').click();
    cy.title().should('eq', pageTitles.speakingPageTitle);
  });

  // Test case to verify that "Keynote Addresses" is present and has correct text
  it('Should verify "Keynote Addresses" text is present on Speaking page', function() {
    const pageTitles = Cypress.env('pageTitles');
    cy.contains('Speaking').click();
    cy.get('.entry-content > :nth-child(2)').should('exist').and('have.text', pageTitles.keynoteText);
  });

  // Cleanup after all tests are completed
  after(function() {
    console.log('All tests have finished executing.');
  });
});
