Feature: Test MIS

  Scenario Outline: Launch MIS Dashboard
    Given Launch browser to open MIS
    Then Enter login credentials where username is "<username>" and "<password>"
    And Verify if MIS is launched and login
    Examples:
      | username   | password|
      |arpit.mishra|Gemini@123|

    Scenario Outline: Check Employee Calendar
      Given Enter login credentials where username is "<username>" and "<password>"
      Then Verify if date "<date>" is shown
      Examples:
        | username | password | date |
      |arpit.mishra|Gemini@123|1 Jan |