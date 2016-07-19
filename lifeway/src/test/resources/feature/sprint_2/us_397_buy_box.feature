Feature: US-397 Buy Box
  The buy box is the summary section of the product purchase. It has all the relevant information regarding pricing and promotions related to this specific product. All the information of the components comes from an API call and has to be rendered by the component.
  The API call provides the product data.
  All labels are mandatory.
  Max and Min quantity messages are mandatory.

  @startProductQA
  Scenario: Displaying a product with multiple SKU options
    Given a product with multiple SKU options
    When is displayed on the product page
    Then the first price line is displayed as a range from the cheapest sku to the most expensive one
    And is followed by the list price
    And a dropdown list is displayed with links to the product pages of each of the SKU options available
    And the shown text is obtained from the product data

  Scenario: Displaying a product with discount
    Given a product with Sale Price on the product data
    When is displayed on the product page
    Then the Sale Price Label is followed by the Sale price amount
    And the Regular Price Label is displayed with "rgba(219, 153, 0, 1)" color, followed by the List price from PIM product data
    And the You Save Label is displayed with "rgba(100, 139, 91, 1)" color, followed by the percentage value of the difference between the Regular price and sale price

  @startProductQA
  Scenario: Displaying common pricing and shipping details from a product
    Given a product page is being requested
    When the Buy box is rendered
    Then if there are bulk discounts available for this component, the Bulk discounts label is displayed with a link that opens a tooltip with the list of discounts
    And under Bulk discounts label the availability message is displayed
    And the Add to cart button is displayed with "Add to Cart" button text as a caption
    And if the product is not buyable, this information comes from the product data, the button is rendered with a grey color and disabled
