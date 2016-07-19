Feature: US-203 Text and Image wide
    This component shows a small text, enhanced by an image and a button link to give the visitor the ability to search for more information

   Scenario: the visitor clicks on the image or the button
      Given The visitor has opened a page with a text and image wide component
      When the visitor clicks on the button
      Then The visitor should be redirected to "URL" property
