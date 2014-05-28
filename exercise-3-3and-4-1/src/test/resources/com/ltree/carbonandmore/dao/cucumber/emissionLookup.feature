Feature: As an associate //TODO: complete
 
Scenario: Querying a large database returns within a reasonable time
Given I have a database of many emissions located in file: "emissions.xml"
When I search for the emission for transport type CAR_FORD_GALAXY 
Then The result should return in under 1000 milliseconds