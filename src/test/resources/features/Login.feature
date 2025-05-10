Feature: SauceDemo Order Flow

  Scenario: User logs in and places an order
    Given User launches SauceDemo site
    When User logs in with "standard_user" and "secret_sauce"
    Then User adds items to the cart
    And User proceeds to checkout and fills information
    And User finishes the order
    Then User should see the message "Thank you for your order!"
