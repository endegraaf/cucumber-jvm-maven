Feature: Calculator
  As a user
  I want to use a calculator
  So that I don't need to calculate myself

  Scenario Outline: Add two numbers
    Given I have a calculator
    When I add <condition1> and <condition2>
    Then the result should be <result1>
    Examples:
        | condition1 | condition2 | result1 |
        | fkjsd      | dlfkd      | ldssd   |
