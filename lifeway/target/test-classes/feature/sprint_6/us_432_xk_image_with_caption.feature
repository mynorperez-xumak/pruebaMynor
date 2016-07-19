Feature: US-432 XK Image with caption
    This component represents an image, whether if it's contained inside a box or a simple image

   @startImageWithCaption
   Scenario: Caption mode set
      Given the author has checked the author mode
      When the component image with caption is rendered
      Then a box should be rendered containing the image
      And at the bottom of the box should be displayed Credit, Caption, Cite vertically from top to bottom in this order

   Scenario: caption mode disabled
      Given an image with the Caption Mode checkbox unchecked
      When the component is rendered
      Then the image is rendered without any box

   Scenario: Large images
      Given the size selected is Large
      When the component image with caption is rendered
      Then the image is centered in the container
      And the alignment is moot

   Scenario: Alignment and Size (added by QA)
      Given the size selected is small 
      And the alignment selected is Right 
      When the component image with caption is rendered
      Then the image will be aligned to the right in a small size

   Scenario: Alignment and Size (Added by QA)
      Given the size selected is medium
      And the alignment selected is left
      When the component image with caption is rendered
      Then the image will be aligned to the left in a medium size