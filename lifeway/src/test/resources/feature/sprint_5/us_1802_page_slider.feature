Feature: US-1802 Page Slider
   This shows a number of items, each item representing the basic information of a page (Title and Description)

   Scenario: The component is rendered
      Given a pages slider already configured
      When the component pages slide is rendered
      Then the Page title, and description is obtained
      And the page title should be rendered as each item title
      And the page description should be rendered as each item summary