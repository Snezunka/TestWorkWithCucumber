Feature: Search domain in google results

  @Smoke
  @Test
  Scenario: Search in Google and check needed domain
    When Open 'Google' page
    Then User is on 'Google' page
    When Search in google word "automation"
    Then User is on 'Search results' page
    And Five first search results pages contain expected domain