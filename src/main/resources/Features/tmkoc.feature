@TMKOCFeature
Feature: TMKOC

Background:
Given Launch Brave Browser

@TMKOC
Scenario Outline: Tarak mehta ka ulta chasma
	  	And Search youtube and open sussessfully
	  When Search "<Episode>"
	  	And I wait Low
	  Then I click on "Youtube_Search"
 	  	And I wait Low
			And I scoll to view "Video_TMKOC_123"
#		Then I verify "Video_TMKOC_123" available on present page
#			And I scoll to view "Video_TMKOC_101"
#		Then I verify "Video_TMKOC_101" available on present page
#			And I scoll to view "Video_TMKOC_107"
#		Then I verify "Video_TMKOC_107" available on present page
#			And I scoll to view "Video_TMKOC_103"
#		Then I verify "Video_TMKOC_103" available on present page
#			And I scoll to view "Video_TMKOC_100"
#		Then I verify "Video_TMKOC_100" available on present page
	  
	  Examples:
	  | Episode     |
	  | TMKOC EP101 |  
	  
#@instahyre
#Scenario Outline: upload resume in instahyre
    #When Search Instahyre and open sussessfully
    #Then I enter "<username>" and "<password>" of instahyre
    #	And I wait Low
#
#
    #Examples: 
      #| username                  | password  |
      #| kishoretarunuem@gmail.com | Qwe123Asd |
      #
#@naukari
#Scenario Outline: upload resume in naukari
#			And I wait Low
#		When Search naukari and open sussessfully
#			And I wait Low
#		Then I enter "<username>" and "<password>"
#			And I wait Low
#		Then I click on "Naukari_Login"
#			And I wait High
#
#		
    #Examples: 
      #| username                  | password  |
      #| kishoretarunuem@gmail.com | Qwe123Asd |
      
     