@NaukariFeature
Feature: Naukari

Background:
Given Launch Brave Browser

@naukari
Scenario Outline: upload resume in naukari
#		Given Launch Brave Browser
			And I wait Low
		When Search naukari and open sussessfully
			And I wait Low
		Then I enter "<username>" and "<password>"
			And I wait Low
		Then I click on "Naukari_Login"
#			And I wait Low
#			And I scoll to view "Naukari_ViewProfile_AboutUs"
#			And I wait Low
#			And I scoll to view "Naukari_ViewProfile"
#			And I wait Low
#		Then I click on "Naukari_ViewProfile"
#			And I wait Low
#		Then I click on "Naukari_Update_resume"
#		Then I upload Resume on Naukari
#			And I switch to frame
#		Then I click on "Naukari_UpdateResume_Resume"
#		Then I verify "Naukari_UpdateResume_Resume" available on present page
#			And I scoll to view "Resume_Headline_text"
#			And I wait Low
#			And I scoll to view "Personal_details_header"
#			And I wait Low
#		Then I verify "Personal_details_header" available on present page
#			And I wait Low
#			And I scoll to view "Naukari_Update_resume"
#			And I wait Low
#		Then I verify "Naukari_Update_resume" available on present page
#			And I wait Low
#			And I scoll to view "Personal_details_header"
#			And I wait Low
#		Then I verify "Personal_details_header" available on present page
			And I wait High
#	  	And Close the Brave browser
		
    Examples: 
      | username                  | password  |
      | kishoretarunuem@gmail.com | Qwe123Asd |