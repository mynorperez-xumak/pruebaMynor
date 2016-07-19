Feature: US-2263 Alignment, social share and buttons functionalities
    Add new alignment functionality to the text and image.
    Add the option to define up to three different action buttons at the bottom of the section.
    Change Body field to a richtext.
    Enable social share buttons

 Scenario: Visitor clicks on main call to action 
      Given the author has set "Main Call to Action URL"
      And the authorr hass sset "Main Call to Action Button textt"
      When the visitor clicks on the blue button
      Then the visitor is redirected to Main Call to Action URL
      And the new page opens as specified in "Bottom Links Target"

Scenario: visitor does not define main call to action
      Given the authors has not set a "Main Call to Action URL"
      When the componente is rendered
      Then no blue button is shown




