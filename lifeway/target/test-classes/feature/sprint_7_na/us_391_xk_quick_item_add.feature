Feature: US-391 XK Quick Item Add
    This toolbox is meant for add an item using it's productID/SKU/Product Code. It contains messaging on error events
    It has it's own RegEx validation for entering correct Product Code


   Scenario: the visitor enters productID and qty and click add
      Given a visitor has entered a product id
      And the product ID is valid
      And sets the qty equal more  the minimum of the product
      And less equal than the maximum of the product
      When the visitor clicks add
      Then The item should be added to the cart
      And the totals should be updated
      And the items in the cart should be updated
      And the cart item should be updated


   Scenario: the product ID is already on the cart
      Given an item is added to the cart
      And the item is already in the cart
      When the add button is clicked
      Then the quantities of both items should be added
      And the cart items should be updated
      And the cart totals should be updated

   Scenario: Field validation
      Given the existence of two fields
      When data is entered
      Then the error message is "Invalid quantity."