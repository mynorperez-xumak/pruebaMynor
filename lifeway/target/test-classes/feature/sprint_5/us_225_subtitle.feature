Feature: US-225 Subtitle
    This component shows the subtitle title of an specific article

   Scenario: the component is rendered
      Given an article page is opened
      When the Article subtitle component is rendered
      Then the article subtitle should be shown under the headline of the article page