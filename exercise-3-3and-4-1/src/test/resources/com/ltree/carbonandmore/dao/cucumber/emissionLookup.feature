Feature: As an associate I want to be able to quickly look up emissions for various vehicle types so that I can plan the environmental impact of my transport.
 
Scenario: Querying a large database returns within a reasonable time
Given I have a database of many emissions located in file: "emissions_high_volume.xml"
When I search for the emission for transport type CAR_FORD_GALAXY 
Then The result should return in under 1000 milliseconds