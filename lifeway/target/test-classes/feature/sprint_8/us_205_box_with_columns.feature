Feature: US-205 Box with columns
    This set of components are meant for creating a full width section filled with a background image an a set of multiple columns.
    These columns can be 3 or 4 columns, each one of those are represented by an instance of Box with columns item component inside a parsys of a Box with columns container component.

   Scenario: The component is rendered in preview mode / publish
      Given the visitor navigates to page with a Box with columns components
      And the Box with columns component has three or more Box with columns items
      When the componente box is rendered
      Then each box with columns item should be rendered as a column
      And there can be shown up to four columns
      And should be rendered as the screenshots  


   Scenario: The visitor clicks on a button
      Given the visitor navigates to page
      And scrolls to the box with columns section
      When the visitor clicks in the button
      Then the vissitor is redirected to URL
      And the redirection occurs in Target specified tab

	
