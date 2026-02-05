@TMKOCFeature
Feature: TMKOC

Background:
Given Launch Brave Browser

@TMKOC
Scenario Outline: Tarak mehta ka ulta chasma
	  When I provide "<URL>" and open successflly
	  When  I provide "<Episode>" in search box "Youtube_Search_Box" 
	  	And I wait "Low"
	  Then I click on "Youtube_Search_Icon"
 	  	And I wait "Low"
			And I scoll to view "Video_TMKOC_123"
		Then I verify "Video_TMKOC_123" available on present page
			And I scoll to view "Video_TMKOC_101"
		Then I verify "Video_TMKOC_101" available on present page
			And I scoll to view "Video_TMKOC_107"
		Then I verify "Video_TMKOC_107" available on present page
			And I scoll to view "Video_TMKOC_103"
		Then I verify "Video_TMKOC_103" available on present page
			And I scoll to view "Video_TMKOC_100"
		Then I verify "Video_TMKOC_100" available on present page
	  
	  Examples:
	  | Episode     |	URL	|
	  | TMKOC EP101 |	URL_Youtube	|

	@TMKOC1
	Scenario Outline: Tarak mehta ka ulta chasma
		When I provide "<URL>" and open successflly
		When  I provide "<Episode>" in search box "Youtube_Search_Box"
		And I wait "Low"
		Then I click on "Youtube_Search_Icon"
		And I wait "Low"
		And I scoll to view "Video_TMKOC_123"
		Then I verify "Video_TMKOC_123" available on present page
		And I scoll to view "Video_TMKOC_101"
		Then I verify "Video_TMKOC_101" available on present page
		And I scoll to view "Video_TMKOC_107"
		Then I verify "Video_TMKOC_107" available on present page
		And I scoll to view "Video_TMKOC_103"
		Then I verify "Video_TMKOC_103" available on present page
		And I scoll to view "Video_TMKOC_100"
		Then I verify "Video_TMKOC_100" available on present page

		Examples:
			| Episode     |	URL	|
			| TMKOC EP101 |	URL_Youtube	|