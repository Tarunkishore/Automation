@FounditFeature
Feature: Foundit Monster

@foundit
Scenario Outline: Delete & Upload resume in foundit Monster
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<URL>" and open successflly
			And I wait "Low"
		Then I verify "Foundit_LoginViaPass" available on present page
		Then I click on "Foundit_LoginViaPass"
		Then I verify "Foundit_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Foundit_UserName"
			And I wait "Low"
		Then I verify "Foundit_Password" available on present page
		When I enter the "<password>" in "Foundit_Password"
    	And I wait "Low"
    Then I click on "Foundit_Login"
	    And I wait for "Low"
	  Then I verify "Foundit_Profile" available on present page
		Then I click on "Foundit_Profile"
		  And I wait for "Low"
		Then I verify "Foundit_ViewProfile" available on present page
		Then I click on "Foundit_ViewProfile"
		  And I wait for "Low"
		Then I verify "Foundit_Resume_Delete_Button" available on present page
		Then I click on "Foundit_Resume_Delete_Button"
		  And I wait for "Very_High"
#		Then I verify "Foundit_Upload_Resume" available on present page
#		Then I click on "Foundit_Upload_Resume"
#		  And I wait for "Low"
		Then I upload Resume on Foundit Monster
			And I wait for "High"

Examples: 
	| username | password |	URL	|
	| UserName | Password	| URL_Foundit_Monster	|