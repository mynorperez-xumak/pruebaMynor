Feature: US-162 XK Social Buttons
    This are the controls to share in twitter, facebook, and send via email the current page


   Scenario: Visitor wants to see social buttons
      Given the visitor wants to see social buttons
      When he navigates to article template
      Then the visitor will be able to the social buttons component displayed

   Scenario: the visitor opens the email modal
      Given the visitor opens the email modal
      When the modal is rendered
      Then the page title should be shown instead the product name