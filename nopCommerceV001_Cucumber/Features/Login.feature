Feature: Login

  @tag1
  Scenario: Successfull Login with Valid Credentials
    Given User launch Chrome browser
    When User open URL "http://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Click on login
    Then Page Title should be "Dashboard / nopCommerce administration"
    When User click on Log Out button
    Then Page Title should be "Your store. Login"
    And close browser

  @tag2
  Scenario Outline: Login Data Driven
    Given User launch Chrome browser
    When User open URL "http://admin-demo.nopcommerce.com/login"
    And User enters email as "<email>" and password as "<password>"
    And Click on login
    Then Page Title should be "<status login>"

    Examples:
    |email|password|status login|
    |admin@yourstore.com|admin|Dashboard / nopCommerce administration|
    |admin1@yourstore.com|admin|Your store. Login                    |

    @tag3
    Scenario: User view login page
      Given User launch Chrome browser
      When User open URL "http://admin-demo.nopcommerce.com/login"
      Then User is on login page