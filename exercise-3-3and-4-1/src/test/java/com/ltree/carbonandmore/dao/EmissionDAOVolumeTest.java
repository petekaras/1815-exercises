package com.ltree.carbonandmore.dao;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;

public class EmissionDAOVolumeTest {
	EmissionDAO emissionDAO = null;
	/* Test User */
	private static final TransportType TRANSPORT_TYPE_ON_SYSTEM = TransportType.CAR_FORD_GALAXY;
	private static final double EXPECTED_EMISSION_FOR_TRANSPORT_TYPE_ON_SYSTEM = 12.61;
	
	static Logger log = Logger.getLogger(EmissionDAOVolumeTest.class.getName());
	
	@Before
	public void setUp() {
		/*
		 * Here we specify which implementation of the EmissionDAO we are using.
		 * In this case we use an XML based DAO for XML Files
		 */
		emissionDAO = new XMLEmissionDAO();
		emissionDAO.setDataSource("/com/ltree/carbonandmore/dao/emissions_high_volume.xml");
	}

	@After
	public void tearDown() {
		emissionDAO = null;
	}

	@Test //(timeout=1000)
	public void shouldFindEmission() {
		long startTime = System.currentTimeMillis();

		double result = emissionDAO.getEmission(TRANSPORT_TYPE_ON_SYSTEM);
		Assert.assertEquals(EXPECTED_EMISSION_FOR_TRANSPORT_TYPE_ON_SYSTEM,
				result,0.01);
		
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		log.info("Duration of test: " + duration);
		
	}
}
