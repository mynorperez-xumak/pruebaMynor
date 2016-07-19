Feature: US-386 XK Contact & Resources
    This set of components shows points of contact and a vertical list of links that can be resources for the visitor to get more information about the topic they are searching for

   Scenario: The visitor wants to see the component displayed
      Given the author adds Contact Us & Helpful Resources in Author
      When the visitor navigates Publish
      Then he will see the component displayed

   Scenario: the visitor clicks on the icon
      Given the visitor navigates to a page with XK Contact & Resources component
      When the visitor clicks on an icon
      Then he is redirected to Icon URL
      And the new page is displayed as "blank" specifies

