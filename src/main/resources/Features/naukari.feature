@NaukariFeature
Feature: Naukari

  #Background:
  #Given Launch Brave Browser
  
@naukari
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
	    And I wait "Low"
	    And I scoll to view "Naukari_ViewProfile_AboutUs"
	    And I wait "Medium"
	    And I scoll to view "Naukari_ViewProfile"
	    And I wait "Low"
    Then I click on "Naukari_ViewProfile"
	    And I wait "High"
	  Then I verify "Naukari_Update_resume" available on present page
    Then I click on "Naukari_Update_resume"
    Then I upload Resume on Naukari
    	And I wait "Low"

Examples: 
	| username | password |	URL	|
	| UserName | Password	| URL_naukari	|

@naukari
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
	  Then I verify "Founfit_Profile" available on present page
		Then I click on "Founfit_Profile"
		  And I wait for "Low"
		Then I verify "Founfit_ViewProfile" available on present page
		Then I click on "Founfit_ViewProfile"
		  And I wait for "Low"
		Then I verify "Foundit_Resume_Delete_Button" available on present page
		Then I click on "Foundit_Resume_Delete_Button"
		  And I wait for "Very_High"
		Then I verify "Foundit_Upload_Resume" available on present page
		Then I click on "Foundit_Upload_Resume"
		  And I wait for "Low"
		Then I upload Resume on Foundit Monster
			And I wait for "High"

Examples: 
	| username | password |	URL	|
	| UserName | Password	| URL_Foundit_Monster	|

 @naukari2
 Scenario Outline: Action class checking
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<NaukariURL>" and open successflly
			And I wait "Low"
		Then I verify "Naukari_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Naukari_UserName"
			And I wait "Low"
		Then I verify "Naukari_Password" available on present page
		When I enter the "<password>" in "Naukari_Password"
    	And I wait "Low"
    Then I click on "Naukari_Login"
		And I wait "Low"
   		And I scoll to view "Naukari_ViewProfile_AboutUs"
    #	And I wait "Low"
    #	And I scoll to view "Naukari_ViewProfile"
    #	And I wait "Low"
    #Then I click on "Naukari_ViewProfile"
    #	And I wait "Low"
    #Then I right click on "Naukari_Job_Tab" and open in new Window
    #And I wait "Low"
    #Then I mouseHover on "Naukari_Job_Tab"
    #Then I mouseHover on "Naukari_Job_Recommended"
    #Then I click on "Naukari_Job_Recommended"
     #	And I wait "Low"
    #	And I select the "Naukari_Job_Recommended_CheckBox"
    #	And I wait "Low"
    #	And I select the "Naukari_Job_Recommended_CheckBox"
    #	And I wait "Low"
    #	And I select the "Naukari_Job_Recommended_CheckBox"
    #	And I wait "Low"
    #	And I select the "Naukari_Job_Recommended_CheckBox" 
    #	And I wait "Low"   	    	    	
    #Then I click on "Naukari_Job_Apply"
    #	And I wait "Low"
    #	And I switch to frame
    #Then I verify "Naukari_Job_Apply_Que_textBox" available on present page
    #When I enter "<text>" in "Naukari_Job_Apply_Que_textBox"

Examples: 
	| username | password |	NaukariURL	|	text |
	| UserName | Password | URL_naukari	|	6		 |

 @naukari1
 Scenario Outline: Action class checking
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<NaukariURL>" and open successflly
			And I wait "Low"
		Then I verify "Naukari_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Naukari_UserName"
			And I wait "Low"
		Then I verify "Naukari_Password" available on present page
		When I enter the "<password>" in "Naukari_Password"
    	And I wait "Low"
    Then I click on "Naukari_Login"
		And I wait "Low"
   		And I scoll to view "Naukari_ViewProfile_AboutUs"
    	And I wait "Low"
    	And I scoll to view "Naukari_ViewProfile"
    	And I wait "Low"
    Then I click on "Naukari_ViewProfile"
    	And I wait "Low"
    #Then I right click on "Naukari_Job_Tab" and open in new Window
    #And I wait "Low"
    Then I mouseHover on "Naukari_Job_Tab"
    Then I mouseHover on "Naukari_Job_Recommended"
    Then I click on "Naukari_Job_Recommended"
     	And I wait "Low"
    	And I select the "Naukari_Job_Recommended_CheckBox"
    	And I wait "Low"
    	And I select the "Naukari_Job_Recommended_CheckBox"
    	And I wait "Low"
    	And I select the "Naukari_Job_Recommended_CheckBox"
    	And I wait "Low"
    	And I select the "Naukari_Job_Recommended_CheckBox" 
    	And I wait "Low"   	    	    	
    Then I click on "Naukari_Job_Apply"
    	And I wait "Low"
    	And I switch to frame
    Then I verify "Naukari_Job_Apply_Que_textBox" available on present page
    When I enter "<text>" in "Naukari_Job_Apply_Que_textBox"

Examples: 
	| username | password | NaukariURL	|	text |
	| UserName | Password | URL_naukari	|	6		 |

