Feature: Search in google and check title of Result page

  @Smoke
  @Test
  Scenario Outline: Search in Google and check First Search Result page
    Given Open 'Google' page
    When Search in google word "<neededText>"
    Then User is on 'Search results' page
    When User select first result link on 'Search results' page
    Then Title of 'Result' page contains "<neededText>"

  Examples:
     | neededText |
     | automation |