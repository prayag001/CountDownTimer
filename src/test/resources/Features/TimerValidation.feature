Feature: Test Timer Validation functionality

Scenario: Check that the count down happens and that the remaining time decreases in one-second increments
Given browser is open
And user is on home page
When user enters timer value in text field
Then verify time is decreased every second till timer is expired
And alert pop is displayed
