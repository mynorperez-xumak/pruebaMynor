Feature: US-395 Cart Sumary
    A product is added to the cart, this pop up modal window should come up with the summary of the items on the current cart.
    Promotional data is not taken in count for this release

   @startProductQAOK
   Scenario: Visitor sees the cart summary
      Given an item has been added to the cart
      When the cart summary is displayed
      Then the total of the items in the cart, the product default image, product name and price should be displayed as the screenshot

   @startProductQAOK
   Scenario: Visitor clicks on continue shopping
      Given the visitor decided to keep shopping after adding an item to his cart
      When he clicks on continue shopping button
      Then the modal window should fade

   @startProductQAOK
   Scenario: Visitor clicks on checkout
      Given the visitor decided to checkout after adding an item to his cart
      When he clicks on checkout button
      Then the modal window should fade. The functionality will be develop later on