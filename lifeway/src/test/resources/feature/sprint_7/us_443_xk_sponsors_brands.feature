Feature: US-443 XK Sponsors & Brands
    This component should be able to be included across the site. The purpose of this component is to show the sponsors and brands that supports an specific christian program. The component should have a main image with a simple text over it, the text can be part of the image or over it

   Scenario: the main image is not configured
      Given the author doesn't configured the "Main Image" field
      When the component xk Sponsors is rendered
      Then the sponsors images should be aligned at the left

   Scenario: a visitor clicks on a sponsor image
      Given the visitor sees a sponsor image
      And the image has the "http://google.com.ar/" field configured
      When the visitor clicks on one sponsor image
      Then the visitor should be redirected to "https://www.google.com.ar/?gws_rd=ssl" page