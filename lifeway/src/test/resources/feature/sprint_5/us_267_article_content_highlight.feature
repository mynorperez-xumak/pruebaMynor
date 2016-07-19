Feature: US-267 Article Content Highlight
   As a/an Article content editor
   I want the ability to add content highlights 
   So that I can summarize the article for the reader

   Scenario: The article page renders the Content Highlight like the configured by the Author
      Given the user navigates to the Article page
      And it contains a Highlight content
      Then I can see the highlight content with the configured format in the Author instance