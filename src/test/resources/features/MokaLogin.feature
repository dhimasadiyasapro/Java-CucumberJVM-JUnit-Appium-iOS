Feature: Moka iOS Login

  Scenario: Can login on Moka iOS App
    Given I am on the Moka Start Page
    When I click on Sign In
    And I input correct login credentials
    And I click on Login Button
    Then I should be on Library Page of Moka iOS App
