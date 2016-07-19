Feature: US-278 Product Slider
    The product slider is meant for full author configured pages on the site.
    This slider shows multiple products to be highlighted for the visitor on specific pages.

   Scenario: click on the name or image of the product
      Given A authored page, like articles
      And a product slider correctly configured
      When a visitor clicks on the title of an item on the slider
      Then the visitor should be redirected to that specific product detail page
      And the product page is open on the current tab

   Scenario: Adding a product that has variants
      Given A product that has been configured on the product slider
      And that product has variants
      And the sale format is not set
      When the product is displayed
      Then the price shown should be the lowest variant price
      And the "Starting at" price label should be shown before the price
      And the text color is the same as the actual price

   Scenario: A product image is not present or broken
      Given A configured product that does not have an image or the product image is broken
      When the item on the slider is rendered
      Then "scene7.com" should provide a placeholder image to display
      And the placeholder image should be used as the product image

   Scenario: Alternative Text of the product images
      Given an item on the slider
      When the item is displayed
      Then the alternative text of the product image should be the product name