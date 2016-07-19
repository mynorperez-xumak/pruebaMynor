Feature: US-318 XK Article Blockquote
    This is a simple text quote to show on article's pages

   Scenario: visitor opens an article page with a quote
      Given an article page is loaded
      When he sees a quote
      Then the quote should show a thin color column
      And the text aligned to it
      And the author of the quote is shown at the bottom after a slash