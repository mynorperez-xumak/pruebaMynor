Feature: US-2359 Login Header Rendering Login Update
    This update enables the link to redirect the visitor to the login form outside Lifeway.com


Scenario: Anonymous user clicks Login/Register Link
      Given an anonymous visitor clicks on Login/Register link on the header
      And the visitor is redirected to the login form outside Lifeway com
      When the visitor completes the login
      Then the visitor is redirected to the homepage of Lifeway com on the

Scenario: logged visitor opens a page on Lifeway.com
      Given a logged visitor navigates to a page
      When the header is rendered
      Then the logged in text is shown
   