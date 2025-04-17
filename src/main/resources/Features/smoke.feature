@smoke
Feature: Smoke Testing 

@smoke @Medium
Scenario Outline: verify UserNmae and Password
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<URL>" and open successflly
			And I wait "Low"
		Then I verify "Naukari_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Naukari_UserName"
			And I wait "Low"
		Then I verify "Naukari_Password" available on present page
		When I enter the "<password>" in "Naukari_Password"
    	And I wait "Low"
    Then I click on "Naukari_Login"
    
Examples: 
	| username | password |	URL	|
	| UserName | Password	| URL_naukari	|	
	
	
@smoke @Medium
Scenario Outline: verify naukari Logo
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<URL>" and open successflly
			And I wait "Low"
		Then I verify "Naukari_logo" available on present page
    
    
Examples: 
	|	URL	|
	| URL_naukari	|		
	
@Low @smoke12
Scenario Outline: Check Box Test Automation Practice
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly
    And I scoll to view "Week_Day_CheckBox"
    And I select "Week_Day_CheckBox"

Examples: 
  | URL						|
  | URL_TestAutomationPractice	|
  
@smoke1
Scenario Outline: How to handle alerts
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly
    
    
    

Examples: 
  | URL						|
  | URL_TestAutomationPractice	|






























































  