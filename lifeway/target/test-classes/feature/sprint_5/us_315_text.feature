Feature: US-315 Text
    Simple paragraph text, this component can be used across the site

   Scenario: The component Text is render
      Given an article page is loaded
      When the component Text is render
      Then id anchor satisfy the regular expression "^[a-zA-Z0-9_]*"