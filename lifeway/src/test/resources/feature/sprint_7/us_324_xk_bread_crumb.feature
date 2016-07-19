Feature: US-324 XK Breadcrumb
    Breadcrumbs are links to structural pages on the site

   Scenario: the visitor clicks on a link
      Given the visitor sees a page with breadcrumbs
      And the breadcrumbs has "Automatic Mode" checkbox unchecked 
      When the visitor clicks on a link
      Then he should be redirected to "URL" page
      And the page is opened as "Target" field specifies