Feature: US-393 Companion Product
    This component should show a list of products that are manually selected for each product. 
    This should be handled by Adobe Target and feeded to AEM via the Product Data.

   Scenario: Visitor sees the component
      Given a product with companion products
      When the page is loaded
      Then the component should be rendered as the screenshot
      And for each item, if has variants, the default SKU option should be preselected

   Scenario: Visitor selects variant
      Given a companion product with variants
      When the visitor selects a variant
      Then the variant info and pricing is updated

   Scenario: Visitor clicks on add to cart
      Given a visitor wants to add a companion product to his cart
      When the visitor clicks on the cart button 
      Then the default sky option is added to the cart
      And the cart summary pop up modal is displayed

   Scenario: Visitor clicks on add to cart_b
      Given a visitor wants to add a companion product to his cart
      When the quantity is <= 0
      And  the visitor clicks on the cart button 
      Then The Max Quantity message is shown
      And the item is not added to the cart

   Scenario: Visitor clicks on a companion product
      Given a product page has been loaded
      When the visitor clicks on a companion product name
      Then the product's page should be loaded on a new tab