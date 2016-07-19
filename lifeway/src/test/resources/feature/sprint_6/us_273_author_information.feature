Feature: US-273 Author Information
    This component is meant for show to the visitor the author information and a list of articles that the author want to show

   @startRelatedArticles
   Scenario: Automatic mode ON
      Given the Automatic mode checkbox is set
      When the component author information is rendered
      Then the author image is loaded from authors contributor page
      And the biography is loaded from author's contributor page
      And the name is loaded from authors contributor page

   Scenario: Automatic mode OFF
      Given the Automatic mode checkbox is not set
      When the component author information is rendered
      Then the author image comes from the local dialog
      And the biography comes from the local dialog
      And the author name comes from the local dialog

   Scenario: No image found
      Given the image is not found
      When the component author information is rendered
      Then the text should be aligned to the left