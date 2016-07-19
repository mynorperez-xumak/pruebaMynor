Feature: 405-Article Headline
   As a visitor
   I want to see the article's title
   So that I can identify that specific article

   Scenario: the component is rendered
      Given an article page is opened
      When the Article Headline component is rendered
      And the data is obtained from the page properties
      Then the article headline should be shown at the top of the article page