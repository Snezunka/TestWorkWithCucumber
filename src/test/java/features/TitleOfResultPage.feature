Feature: Search in google and check title of Result page

  @Smoke
  @Test
  Scenario: Search in Google and check First Search Result page
    When Open 'Google' page
    Then User is on 'Google' page
    When Search in google word "automation"
    Then User is on 'Search results' page
    When User select first result link on 'Search results' page
    Then Title of 'Result' page contains "automation"