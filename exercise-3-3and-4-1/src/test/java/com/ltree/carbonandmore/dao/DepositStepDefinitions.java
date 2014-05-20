package com.ltree.carbonandmore.dao;
 
import org.junit.Assert;

import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
 
public class DepositStepDefinitions {
	
	EmissionDAO emissionDAO = null;
	long measuredTime = 0;
	
	@Given("^I have a database of many emissions located in file: \"([^\"]*)\"$")
	public void I_have_a_database_of_many_emissions(String fileName) {
		emissionDAO = new XMLEmissionDAO();
		emissionDAO.setDataSource("/com/ltree/carbonandmore/dao/emissions_high_volume.xml");
		Assert.assertTrue(true);
	}
 
	@When("^I search for the emission for transport type CAR_FORD_GALAXY$")
	public void I_search_for_the_emission_for_transport_type_CAR_FORD_GALAXY() {
		long startTime = System.currentTimeMillis();
		emissionDAO.getEmission(TransportType.CAR_FORD_GALAXY);		
		long endTime = System.currentTimeMillis();
		measuredTime = endTime - startTime;
		Assert.assertTrue(measuredTime > 0);
	}

	@Then("^The result should return in under (\\d+) milliseconds$")
	public void The_result_should_return_in_under_milliseconds(int specifiedTime) {
		Assert.assertTrue(measuredTime < specifiedTime);
	}
}