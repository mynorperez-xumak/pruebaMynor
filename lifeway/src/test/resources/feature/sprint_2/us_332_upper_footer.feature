Feature: US-332 Upper Footer
  This component is meant to save the configuration to be referenced by the Footer Rendering component (LW-671)
  The component displays the information in a structured informational way.

  @startUpperFooter
  Scenario: An Author enters the links of a column.
    Given The Author has opened the local dialog of the component
    And adds a new element on the Columns multifield
    And adds the Column Title: "Leyend Test QA" property
    And adds a new element on the Links multifield, part of Columns multifield
    And adds the link's "Link 1" property
    And adds an internal or external link on URL: "http://xumak.com/site/en.html" property
    And checks the link's Is Special Link property if the link should be highlighted or not
    When the Author finishes the configuration and click on OK
    Then For each column added, it's list of links should be displayed under the column's Column Title: "Leyend Test QA"
    And if a link's Is Special Link property is checked, the Link Text should be displayed with different color
