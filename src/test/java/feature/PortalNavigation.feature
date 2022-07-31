Feature: Demo BNZ Portal Page Feature

Scenario Outline: Verify navigate to Payees page using the navigation menu works
Given DemoBNZ Webpage is opened
When I Select Payees from Menu
Then Payee page should be loaded
And Close the Browser

Scenario Outline: Verify Payee can be added
Given DemoBNZ Webpage is opened
And I Select Payees from Menu
When I Add Payee details
Then Payee should get added
And Close the Browser

Scenario Outline: Verify Payee name is required field
Given DemoBNZ Webpage is opened
And I Select Payees from Menu
When I Add Payee details with No values entered
Then Validation error message displayed for Payee Name
When I Add Payee details with mandatory fields entered
Then Validation error message is not displayed
And Close the Browser

Scenario Outline: Verify Payee can be added and order by Ascending/Descending
Given DemoBNZ Webpage is opened
And I Select Payees from Menu
When I Add Payee details
Then Payee should get added
And Payees list should be displayed in "ascending order"
When I Click Name Header
Then Payees list should be displayed in "descending order"
And Close the Browser

Scenario Outline: Verify Payment can be completed successfully
Given DemoBNZ Webpage is opened
And I Save current balance of Everyday and Bills Account
When I make "$500.00" Payment Transfer from Everyday to Bills Account
Then Transfer should get completed successfully
And Current balance of Everyday and Bills Account should be correct
And Close the Browser