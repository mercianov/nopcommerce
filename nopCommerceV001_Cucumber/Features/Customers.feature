Feature: Add new customer

  @tag1
  Scenario: Add new customer
    Given User launch Chrome browser
    And User open URL "http://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Click on login
    And User can view dashboard
    When User click on customers menu
    And click on customers menu item
    And click on add new button
    Then user can view add new customer page
    When user enter customer info
    And click on save button
    Then user can view confirmation message "The new customer has been added successfully"
    And close browser

  @tag2
  Scenario: View customer page
    Given User launch Chrome browser
    And User open URL "http://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Click on login
    And User can view dashboard
    When User click on customers menu
    And click on customers menu item
    Then user can view customer page
