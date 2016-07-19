Feature: US-169 Editors note
    This component shows a quote from the editor

Scenario: The component Editors note is rendered
      Given an article page is loaded
      When the component editors note is rendered
      Then I can see the editor note content with the configured format in the Author instance