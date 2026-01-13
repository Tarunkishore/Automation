@instahyreFeature
Feature: Istahyre

@instahyre
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
    	And I wait for "Very_High"
    	And I scoll to view "instahyre_Resume"
    	And I wait for "Very_High"
    Then I click on "instahyre_Resume_upload"
    	And I wait for "Very_High"
    Then I upload Resume on Naukari
    #	And I update resume for instahyre

    Examples: 
      | username | password | URL							|
      | UserName | Password | URL_Instahyre1	|
      
@instahyre1
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
    	And I wait "Low"
    Then I verify "Instahyre_TarunSQA" available on present page
    Then I click on "Instahyre_TarunSQA"
    	And I wait "Low"
    Then I verify "Instahyre_JobList" available on present page
    Then I click on "Instahyre_JobList"
			And I wait "Low"
		Then I verify "Instahyre_Apply" available on present page	
    Then I click "<ApplyCount>" times on "Instahyre_Apply"
    	And I wait "High"

    Examples: 
      | username | password | URL						|	ApplyCount	|
      | UserName | Password | URL_Instahyre1	| 5	|
      
      
@instahyre1
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
    Then I verify "Instahyre_JobList" available on present page
    Then I click on "Instahyre_JobList"
#			And I wait "Low"
		Then I verify "Instahyre_Apply" available on present page	
    #Then I click "<ApplyCount>" times on "Instahyre_Apply"
    	And I wait "High"
#		Then I verify "Instahyre_Searchjobs_Down" available on present page
#			And I will click on "Instahyre_Searchjobs_Down" or "Instahyre_Searchjobs_Up" only when it is down
    Then I click "Instahyre_Apply" job is available 
    	And I wait "High"

    Examples: 
      | username | password | URL						|
      | UserName | Password | URL_Instahyre1	|
      
      