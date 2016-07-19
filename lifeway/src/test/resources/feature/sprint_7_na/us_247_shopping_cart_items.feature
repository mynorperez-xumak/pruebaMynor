Feature: US-247 XK Shopping Cart Items
    This component shows the list of the items on the current cart, this element should show items on an anonymous cart

   Scenario: the visitor sees his cart
      Given the visitor has added items to a cart
      When the visitor enters the page with XK shopping cart items
      Then the component should show a list of every item added by the visitor
      And shows the information about the items
      And the items shows an editable field of quantity


   Scenario: The visitor removes an item
      Given the visitor navigates to the page with XK Shopping cart items component
      And focus on an item on the cart
      When the visitor clicks on the link to remove item
      Then the item should be removed from the cart
      And the quantity does not matter

   Scenario: The visitor sets 0 to QTY field on an item
      Given the visitor navigates to the page with XK Shopping cart items component
      And the cart has more than one item
      When the visitor sets 0 to QTY field of one or more of the items
      And loses focus on the field
      Then the item(s) should be removed from the cart.

   Scenario: One of the products in the cart does not have an image
      Given the component is rendered
      And one or more of the items in the cart does not has a product image
      When the image should be rendered
      Then a placeholder image by Scene7 should be shown

   Scenario: The visitor modifies the QTY field of one or more items in the cart
      Given the visitor navigates to the page with XK Shopping cart items component
      And modifies the QTY field of one or more items
      And loses focus on the field
      Then the items should be updated showing the new totals

   Scenario: The visitor enters non digit character on the qty field or number < 0
      Given the visitor navigates to the page with XK Shopping cart items component
      And enters a non digit character on the QTY field
      When the field is edited 
      Then "Error on field" message should be shown under the QTY field

   Scenario: The visitor clicks on a product name
      Given the visitor navigates to the page with XK Shopping cart items component
      And focus on an item
      When the visitor clicks on the item's name
      Then he should be redirected to the product's page
      And the page should be opened on the same tab.

   Scenario: The cart is empty
      Given the visitor navigates to the page with XK Shopping cart items component
      And the cart has no items
      When the visitor opens the page
      Then no items should be shown
      And "Cart empty msj" should be shown
      And "Continue shopping link" should point to the previous page


