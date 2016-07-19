Feature: 414-Banner Add
   As a visitor 
   I want to click on the banner
   So that I can navigate to a page of my interest.

   Scenario: A visitor clicks on the banner image
      Given a visitor navigates to a page with a banner
      When the visitor clicks on the banner
      Then he should be redirected to the configured URL.