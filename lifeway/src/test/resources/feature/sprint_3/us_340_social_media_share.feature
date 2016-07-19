Feature: US-340 Social Media Share
  The social share component, displays a set of options for sharing the current product page, it can be a share on different social media apps, an email or simply by printing the current page.
  The component should be configured on the design dialog for being used on different pages.

  @startProductQA
  Scenario: the visitor enters incorrectly an email field
    Given the visitor is sending a sharing email
    When he enters incorrectly the email, or does not sepparete with commas the lists of emails on the "my_email.com" field
    Then "Enter a valid email" message under the field should come up
    And the back ground color of the message section is "rgba(252, 236, 152, 1)"

  @startProductQA
  Scenario: Visitor sends an email (Defined on LW-1217)
    Given the visitor has clicked on the email icon
    And has filled correctly the pop up form
    When the visitor passes captcha and clicks the submit button
    Then an email is sended using the layout configured under etc/static/emaillayouts/

  @startProductQA
  Scenario: Visitor uses captcha functionality (Defined on LW-1222)
    Given The visitor needs to send an email
    When he wants to submit the email form
    And passes the captcha widget
    Then he is able to submit the form
    And the email is sent

  @startProductQA
  Scenario: Visitor fills the email form incorrectly
    Given the visitor is entering the fields for the email form
    And the email field is blank
    And the personal message text área is blank
    And your name field is blank
    And your email field is blank
    When any of the required fields marked with the carácter "*" are not present
    Then Enter a valid email message under the field should come up
    And the back ground color of the message section is orange

  @startProductQA
  Scenario: visitor clicks on "send me a copy" check box
    Given the visitor has filled correctly the email form
    When the send me a copy check box is checked
    Then a copy is sent to your email field email data

  @startProductQA
  Scenario: visitor clicks on the close icon of the modal or outside the modal
    Given the visitor has opened the email share modal
    When the visitor has clicked on the close icon of the modal or has clicked outside the modal window
    Then The modal is closed
    And the information is not saved
