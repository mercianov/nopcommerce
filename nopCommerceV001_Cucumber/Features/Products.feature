Feature: View Product Catalog

  @tag122
  Scenario: View product catalog
    Given User launch Chrome browser
    And User open URL "http://admin-demo.nopcommerce.com/login"
    And User enters email as "admin@yourstore.com" and password as "admin"
    And Click on login
    When User click on catalog menu
    And User click on products menu item
    Then User is on product page

