@tag
Feature: Purchase the Order from ECoomerce website.
  I want to use this template for my feature file
  
  Background:
  Given I landed on Ecommerce Page

  @Regression
  Scenario Outline: Positive Test for Submit the Order 
    Given Logged in with username <name>  and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  | password | productName  |
      | ram.kumar@gmail.com | RAM@k123 | ZARA COAT 3 |
      