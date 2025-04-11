@smoke
Feature: Smoke Testing 

@smoke @Medium
Scenario Outline: upload resume in naukari
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