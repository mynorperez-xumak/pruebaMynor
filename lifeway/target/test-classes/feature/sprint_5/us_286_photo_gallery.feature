Feature: US-286 Photo Gallery
    This carousel shows multiple images configured by Author. It can show a title and a text under the carousel

   Scenario: The article page renders the photo gallery like the configured by the Author
      Given the user navigates to the Article page
      And it contains a photo gallery
      Then I can see the photo gallery with the configured format in the Author instance