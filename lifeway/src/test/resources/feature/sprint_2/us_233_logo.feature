Feature: US-233 Logo
  This component is meant to save the configuration to be referenced by the Header Rendering component (LW-670)
  The component displays the information in a structured informational way.
  The lifeway  header logo, represents the main logo of the Lifeway site.

  @startLogoComponent
  Scenario: The author configures the logo image
    Given the author has opened the local dialog
    And adds the logo image "LW-233-Logo"
    And enters the Logo URL: "http://xumak.com/site/en.html" property
    And enters the Logo Alt Text: "Description about logo" optional property
    When finishes the configuration and click OK
    Then the logo: "LW-233-Logo.png" is displayed with a link pointing to "http://xumak.com/site/en.html"
