Feature: US-360 XK Table
    Table component will use an extension of the RTE plug-in and use the HTML structure and style from Lifeway.com. The table creation and functionality will be provided using the RTE Table Inline editor

   Scenario: Visitor navigates to XK Table component
      Given The author has configured a XK Table component
      And The visitor opens a page with a XK Table component
      When the component xk table is rendered
      Then the Table should be shown, with a head and body
      And the table contains the rows and columns defined by the author