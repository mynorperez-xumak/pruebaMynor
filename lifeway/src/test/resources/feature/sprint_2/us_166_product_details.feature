Feature: US-166 Product details
  The product detail component, displays the main information about an specific product. All the information of the components except lables comes from an API call to be rendered by the component.
  The product media is obtained by an specific API call for media content.
  All labels are mandatory.

   @startProductQA
   Scenario: Visitor interacts with image/video main thumbnail section
      Given the visitor is on the product page
      When the visitor clicks on the main image
      Then if the thumbnail is a simple image, a modal section with the clicked image at full size is displayed
      And if the thumbnail is a video, the modal section displays the video player

   Scenario: visitor clicks on an author/contributor link
      Given the visitor is on the product page
      When the visitor clicks on the name of a contributor
      Then is redirected to the contributor page
