Feature: Search domain in google results

  @Smoke
  @Test
  Scenario: Search in Google and check needed domain
    Given Open 'Google' page
    When Search in google word "automation"
    Then User is on 'Search results' page
    Then Five first search results pages contain expected domain