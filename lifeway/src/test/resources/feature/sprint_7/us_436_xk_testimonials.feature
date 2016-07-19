Feature: US-436 XK Testimonials
    Testimonials are quotes from different users, contributors and people in general that want's to share their testimonial regarding a specific topic from the site

   Scenario: the visitor clicks on load more button
      Given the visitor is on the testimonials section
      And the "Items" number is greater than nine
      When the visitor clicks on the load more button
      Then six more items should be loaded asynchronously if its possible

   Scenario: there are no items to load
      Given the component has loaded all the configured "Items"
      When the visitor clicks in the load more button 
      Then the button should disappear
      And no new items are displayed