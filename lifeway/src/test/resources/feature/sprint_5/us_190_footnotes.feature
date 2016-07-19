Feature: US-190 Footnotes
    This is a simple text component that shows footnotes for the current article

   Scenario: Visitor hovers over anchor link
      Given the visitor opens an article page with footnotes
      And at least one of the footnotes has anchorlink
      When the visitor hovers over the anchor link
      Then the tooltip should be visible