Feature: US-1904 XK Article Summary
   As a visitor
   I want to see a summary of the article
   So that I can decide if I'm interested or not

   Scenario: rendering the component
      Given the visitor opens an article page with article summary component
      When the article summary component is rendered
      And has obtained the page description data from the page properties
      Then the article summary is shown as the screenshot