Feature: US-250 XK Most Recent
    This component show a list of recent articles

   Scenario: a visitor sees the component and clicks on a link
      Given the visitor have seen a Most recent component
      When he clicks on an article title link
      Then the visitor is directed to that specific article page