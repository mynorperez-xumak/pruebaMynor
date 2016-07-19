Feature: US-2336 Sponsors and Brands Update
    Update component to handle columns width by the author

   Scenario: Visitor should be able to see Sponsors & Brands organized by columns
      Given the Author has configured the columns as you can see in the image below
      When the visitor navigates to publish
      Then the visitor should be able to see images width according to columns configured by the author
