Feature: ECS Test

  As an Interview Candidate
  I want to complete the ECS Test
  So that i can get a new Job :-)

  Scenario Outline: Complete the ECS test to the best of my ability

    Given I am using the <browser> browser
    And I am on the Welcome Section of the ECS Test Homepage
    And I navigate to the Challenge Section of the ECS Test Homepage
    And I execute the challenge on the Challenge Section
    When I submit my answers along with my name Lloyd Asamoah
    Then a successful message is Displayed


    @Chrome
    Examples: Chrome
    |browser|
    |Chrome|


#    @Firefox
#    Examples: Firefox
#    |browser|
#    |Firefox|