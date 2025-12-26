@instahyreFeature
Feature: Istahyre

@instahyre1
Scenario Outline: upload resume in instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly
    	And I wait "High"
		Then I verify "Instahyre_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Instahyre_UserName"
			And I wait "Low"
		Then I verify "Instahyre_Password" available on present page
		When I enter the "<password>" in "Instahyre_Password"
    	And I wait "Low"
    Then I click on "Instahyre_Login_button"
    	And I wait "High"
    Then I click on "Profile_Tab_header"
    	And I wait "High"
    	And I scoll to view "instahyre_Resume"
    #	And I update resume for instahyre
    	And I scoll to view "Job_Preferences"
    	And I scoll to view "Diversity_info"
    	And I scoll to view "Job_Preferences"
    	And I scoll to view "Diversity_info"
    		And I scoll to view "Job_Preferences"
    	And I scoll to view "Diversity_info"
    	And I scoll to view "Job_Preferences"
    	And I scoll to view "Diversity_info"
    	And I wait "High"

    Examples: 
      | username | password | URL							|
      | UserName | Password | URL_Instahyre1	|
      
@instahyre
Scenario Outline: SQA Job Apply in Instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly
    	And I wait "High"
		Then I verify "Instahyre_UserName" available on present page
			And I wait "Low"
		When I enter the "<username>" in "Instahyre_UserName"
			And I wait "Low"
		Then I verify "Instahyre_Password" available on present page
		When I enter the "<password>" in "Instahyre_Password"
    	And I wait "Low"
    Then I click on "Instahyre_Login_button"
    	And I wait "High"
    Then I verify "Instahyre_Searchjobs" available on present page
    Then I click on "Instahyre_Searchjobs"
    	And I wait "Low"
    	Then I verify "Instahyre_TarunSQA" available on present page
    Then I click on "Instahyre_TarunSQA"
    	And I wait "Low"
    Then I verify "Instahyre_View" available on present page
    Then I click on "Instahyre_View"
			And I wait "Low"
    Then I click "<ApplyCount>" times on "Instahyre_Apply"
    	And I wait "High"

    Examples: 
      | username | password | URL						|	ApplyCount	|
      | UserName | Password | URL_Instahyre	| 5	|
      
      
      
      
      
      