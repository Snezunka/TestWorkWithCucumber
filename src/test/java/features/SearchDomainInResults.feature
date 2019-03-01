Feature: Search domain in google results

  @Smoke
  @Test
  Scenario Outline: Search in Google and check needed domain
    Given Open 'Google' page
    When Search in google word "automation"
    Then <numberOfResultPages> first search results pages contain "<expectedDomain>" domain

  Examples:
  | numberOfResultPages | expectedDomain |
  | 5 | testautomationday.com |