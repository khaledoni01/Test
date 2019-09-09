@tag
Feature: Title of your feature
  I want to use this template for my feature file

  @tag1
  Scenario: Scenario with Datatable
    Given Navigate to Yahoo
    ##Feature.xlsx@Sheet1
    When Find the dataTable

  @tag2
  Scenario Outline: Login to Facebook
    Given Navigate to Facebook site
    When I provide username and password

    Examples:
    ##Feature.xlsx@Sheet1

  @tag3
  Scenario Outline: Search in Google
    Given Navigate to Google engine
    When I provide search string

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |

