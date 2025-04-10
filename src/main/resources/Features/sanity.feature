@sanity
Feature: Sanity Testing
  
@Low @naukari
Scenario: Launch Browser Test
    Given Launch Brave Browser
    
@Low @naukari
Scenario Outline: upload resume in naukari
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<URL>" and open successflly
			And I wait "Low"
    
Examples: 
	|	URL	|
	| URL_naukari	|
	

@Low @instahyre1
Scenario Outline: upload resume in instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly

Examples: 
  | username | password | URL							|
  | UserName | Password | URL_Instahyre1	|
      
@Low @instahyre
Scenario Outline: SQA Job Apply in Instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly

Examples: 
  | username | password | URL						|
  | UserName | Password | URL_Instahyre	|    
     	