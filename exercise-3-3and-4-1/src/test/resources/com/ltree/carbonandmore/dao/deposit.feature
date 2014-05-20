Feature: As an associate I want to be able to calculate emissions for my vehicles easily and quickly so that I can make informed travel decisions
 
Scenario: Querying a large database returns within a reasonable time
Given I have a database of many emissions located in file: "emissions_high_volume.xml"
When I search for the emission for transport type CAR_FORD_GALAXY 
Then The result should return in under 50 milliseconds