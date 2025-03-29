@NaukariFeature
Feature: Naukari

  #Background:
  #Given Launch Brave Browser
  
@naukari
Scenario Outline: upload resume in naukari
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
	    And I wait "Medium"
	    And I scoll to view "Naukari_ViewProfile"
	    And I wait "Low"
    Then I click on "Naukari_ViewProfile"
	    And I wait "High"
    Then I click on "Naukari_Update_resume"
    Then I upload Resume on Naukari
  	  And I switch to frame
    Then I click on "Naukari_UpdateResume_Resume"
    	And I wait "Low"

Examples: 
	| username      | password  				|	NaukariURL	|
	| Naukari_User1 | Naukari_Password1 | URL_naukari	|
  
@naukari3
Scenario Outline: Naukari hidden user password
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

Examples: 
	| username      | password  				|	NaukariURL	|
	| Naukari_User1 | Naukari_Password1 | URL_naukari	|

  
  @naukari1
  Scenario Outline: Action class checking
    Given Launch Brave Browser
    	And I wait "Low"
    When I Search naukari and open successflly
   		And I wait "Low"
    #Then I enter "<username>" and "<password>"
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
	| username                  | password  |	text	|
	| kishoretarunuem@gmail.com | Qwe123Asd |	6	|

  @naukari2
  Scenario Outline: update profile in naukari
    Given Launch Brave Browser
    	And I wait "Low"
    When I Search naukari and open successflly
    	And I wait "Low"
    #Then I enter "<username>" and "<password>"
    	And I wait "Low"
    Then I click on "Naukari_Login"
    	And I wait "Low"
    	And I scoll to view "Naukari_ViewProfile_AboutUs"
    	And I wait "Low"
    	And I scoll to view "Naukari_ViewProfile"
    	And I wait "Low"
    Then I click on "Naukari_ViewProfile"
    	And I wait "Low"
    	And I scoll to view "Resume_Headline_text"
    	And I wait "Low"
    	And I scoll to view "Personal_details_header"
    	And I wait "Low"
    Then I verify "Personal_details_header" available on present page
    	And I wait "Low"
    	And I scoll to view "Naukari_Update_resume"
    	And I wait "Low"
    Then I verify "Naukari_Update_resume" available on present page
    	And I wait "Low"
    	And I scoll to view "Personal_details_header"
    	And I wait "Low"
    Then I verify "Personal_details_header" available on present page
    	And I wait "High"
    #	And Close the Brave browser

Examples: 
	| username                  | password  |
	| kishoretarunuem@gmail.com | Qwe123Asd |
