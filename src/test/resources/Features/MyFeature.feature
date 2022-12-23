@automationCheck
Feature: Automation Practice Site

  Background: Navigation to the URL
    Given User navigate to URL and open the landing page

  @URLRedirection
  Scenario: User navigate to URL, User redirect to landing page with expected current URL
    When User is on landing page
    Then Validate current URL of landing page with expected URL

  @LandingPageTitle
  Scenario: User naviaget to URL, User redirect to landing page with expected page title
    When User is on landing page
    Then Validate title of landing page with expected title as "Products – Automation Practice Site"

  @DisplayLogo
  Scenario: User able to see logo of application on landing page
    When User is on landing page
    Then User see the logo of application

  @LogoHeight
  Scenario: Logo present on landing page with specific height dimension
    When fetch the height of logo
    Then Height of logo should be "50"

  @LogoWidth
  Scenario: Logo present on landing page with specific width dimension
    When fetch the width of logo
    Then Width of logo should be "50"

  #@Shop @test
  #Scenario: User click on Shop link and navigate to respective page and see the Shopname
  #Given User click on Shop Link
  #When navigate to Shop page
  #Then User see the Shop "Product – Automation Practice Site"
  @ProductCategory
  Scenario: User able to see product category on landing page
    When User see the product category
    Then Validate product category as per expected product category listed below
      | Android (1)    |
      | HTML (3)       |
      | JavaScript (3) |
      | selenium (1)  |
    And Size of product category should be 4

  @MyAccountLogin
  Scenario Outline: User is able to login into the application
    Given User click on MyAccount from home page
    When User redirected to login page of the application where title us "Products – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on Login button
    Then User successfully login and landing to next page

    Examples: 
      | username               | password   |
      | suchitatayde@gmail.com | dilip@1234 |
      | suchitawadhe@gmail.com | shrau@1234 |
      | dp.wadhe@gmail.com     | suchi@1234 |

  @loginNegative
  Scenario Outline: User is unable to login into the application
    When User click on MyAccount from home page
    And User redirected to login page of the application where title us "Products – Automation Practice Site"
    And User enters "<username>" and "<password>" and click on Login button
    Then User is unable to login with an error message "Authentication failed."

    Examples: 
      | username            | password |
      | testuser1@gmail.com |   123456 |
      | testuser2@gmail.com |   123456 |
