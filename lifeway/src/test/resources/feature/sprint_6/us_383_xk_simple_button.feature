Feature: US-1904 XK Simple Button
    This is a simple button, to be included in any parsys of the site

   @startSimpleButton
   Scenario: The visitor clicks the button
      Given the visitor sees a button
      When the visitor clicks the button
      Then the visitor should redirect to "URL"

   @startSimpleButton
   Scenario: Button Color (Added by QA) Primary
      Given Button: "/html/body/div[3]/div/div/div[1]/div/div/div[1]/a", Color field has been configured in Author with "Primary" option 
      When the visitor navigate to Two Column Page
      Then the button should: "/html/body/div[3]/div/div/div[1]/div/div/div[1]/a" display in "rgba(219, 153, 0, 1)"

   Scenario: Button Color (Added by QA) Secondary
      Given Button: "/html/body/div[3]/div/div/div[1]/div/div/div[2]/a", Color field has been configured in Author with "Secondary" option 
      When the visitor navigate to Two Column Page
      Then the button should: "/html/body/div[3]/div/div/div[1]/div/div/div[2]/a" display in "rgba(34, 93, 153, 1)"

   Scenario: Button Color (Added by QA) Secondary
      Given Button: "/html/body/div[3]/div/div/div[1]/div/div/div[3]/a", Color field has been configured in Author with "ALT" option 
      When the visitor navigate to Two Column Page
      Then the button should: "/html/body/div[3]/div/div/div[1]/div/div/div[3]/a" display in "rgba(150, 15, 26, 1)"

   Scenario: Button Color (Added by QA) Default
      Given Button: "/html/body/div[3]/div/div/div[1]/div/div/div[4]/a", Color field has been configured in Author with "Simple Button" option 
      When the visitor navigate to Two Column Page
      Then the button should: "/html/body/div[3]/div/div/div[1]/div/div/div[4]/a" display in "rgba(238, 238, 238, 1)"