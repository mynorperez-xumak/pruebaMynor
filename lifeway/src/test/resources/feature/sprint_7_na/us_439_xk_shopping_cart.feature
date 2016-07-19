Feature: US-439 XK Shopping Cart
    The icon on the top right corner of the site, shows the content of the items in the current cart and gives a link to the shopping cart page to manage the items and proceed to checkout

   Scenario: the visitor clicks on the icon
      Given a page opened by an anonymous or logged user
      When the visitor clicks on the shopping cart icon
      Then should be redirected to the shopping cart page

   Scenario: an item is added/removed to the cart
      Given an anonymous or logged user
      When he adds an item to the cart
      Then the cart should show a red circle with the total number of items on the current cart
      And is updated when the page is loaded

   Scenario: there is no items on the cart
      Given a visitor with no items on his cart
      When the shopping cart icon is shown
      Then it should not have the red circle