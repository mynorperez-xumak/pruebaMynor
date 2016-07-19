Feature: US-1193 Buy box add to cart update
    Add functionality to Buy box component for the Add to cart button.

   Scenario: Visitor add product to cart
      Given a visitor wants to add an item to his cart
      When he clicks on an add to cart button
      Then an asynchronous call should be made to the API Gateway function to add an item to the current cart
      And the cart current state should be received as a response
      And the cart summary modal window should fade in showing the cart summary

   Scenario: The product is only buyable in stores
      Given a product that is only buyable in stores
      When the page is loaded
      Then the add to cart button should be disabled 
      And showing a gray color
      And the availability message should be present saying that is only available on stores

   Scenario: The product does not have a price
      Given a product without price
      When the page is loaded
      Then the add to cart button should be disabled 
      And showing a gray color

   Scenario: The product is unavailable
      Given an unavailable product
      When the page is loaded
      Then the add to cart button should be disabled 
      And showing a gray color