@sanity
Feature: Sanity Testing
  
@Low @sanity
Scenario: Launch Browser Test
    Given Launch Brave Browser
    
@Low @sanity
Scenario Outline: upload resume in naukari
    Given Launch Brave Browser
	    And I wait "Low"
    When I provide "<URL>" and open successflly
			And I wait "Low"
    
Examples: 
	|	URL	|
	| URL_naukari	|
	

@Low @sanity
Scenario Outline: upload resume in instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly

Examples: 
  | URL							|
  |	URL_Instahyre1	|
      
@Low @sanity
Scenario Outline: SQA Job Apply in Instahyre
    Given Launch Brave Browser
    When I provide "<URL>" and open successflly

Examples: 
  | URL						|
  | URL_Instahyre	|   
  
  
  
 
     	