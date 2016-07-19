Feature: US-314 XK Category Grid
    This component shows a set of various elements for internal advertisement or informational purposes

   Scenario: The button text is not specified on elements
      Given no text was specified on Button text field
      When the element is rendered
      Then No button should be shown

   Scenario: The visitor clicks on an image or element title
      Given the visitor has clicked an element's image or has clicked an element's title
      When the action is called
      Then the visitor is redirected to "https://www.google.com.ar"
      And the target should be as specified on "blank"

   Scenario: visitor clicks on a blue button of categories
      Given the component has more than one category specified
      And the categories are shown as blue buttons at te top of the section
      When the visitor clicks on a button
      Then the elements of the clicked category should be shown at the bottom