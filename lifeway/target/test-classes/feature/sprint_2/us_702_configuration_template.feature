Feature: US-702 Configuration template

  @startConfigurationTemplate
  Scenario: The author needs to create a page to insert the header and footer configuration components.
    Given the author is configuring header and footer
    And has opened the dialog to create a page
    When he selects the template Configuration template
    Then a page is created with only one parsys to insert configuration components
