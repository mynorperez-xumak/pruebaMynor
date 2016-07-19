Feature: US-293 Top Navigation
  This component is meant to save the configuration to be referenced by the Header Rendering component (LW-670)
  The component displays the information in a structured informational way.
  The top navigation component is a section from the header, that displays a list of links, these links can be internal or external.

  @startTopNavigationComponent
  Scenario: an author is configuring the top navigation links
    Given the author has opened the local dialog
    And adds an element of the Link List multifield
    And adds the LinkText: "Google GT" property of the element
    And adds the LinkUrl: "https://www.google.com.gt/" property of the element
    When finishes the configuration and click OK
    Then the top navigation should be displayed.
