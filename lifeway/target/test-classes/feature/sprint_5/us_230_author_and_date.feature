Feature: US-230 Author and Dates
   As a Visitor
   I want to see the author and publish date of the article
   So that I can see how much time has passed since it's publication

   Scenario: The component is rendered
      Given an article page is loaded
      When the component is rendered
      And the data is obtained from the page properties
      Then the name of the author is shown
      And the name has a link to the author's page
      And the created date is shown in the format: "EEEE, d MMMM, yyyy h:mm a"