Feature: US-403 Text Banners
    This component show 3 banners with title, text and link, the images and subtitles are optional, if one is missing is not shown

   Scenario: The visitor clicks on the button or image of a banner
      Given the visitor sees the banners
      When the visitor clicks on the image or the button of one
      Then the visitor is redirected to URL