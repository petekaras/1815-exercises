package com.ltree.carbonandmore;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ltree.carbonandmore.CarbonDioxideCalculator;
import com.ltree.carbonandmore.dao.EmissionDAO;
import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;


public class CarbonDioxideCalculatorEasyMockTest {
	CarbonDioxideCalculator carbonMonoxideCalculator = null;
	EmissionDAO emissionDAO = null;
    private static final double EPSILON = 0.01;
    private static final double CAR_FORD_MONDEO_HATCHBACK_EMISSION = 109;
	
    @Before
	public void setUp(){	
		/* Create a mockDAO class */
    	//TODO: create the mock object
    	emissionDAO = null;//createMock(EmissionDAO.class);	
		carbonMonoxideCalculator = new CarbonDioxideCalculator(emissionDAO);
		
		/* Set the expectation on the mock */
		//TODO: Change the expected return 
		expect(emissionDAO.getEmission(TransportType.CAR_FORD_MONDEO_HATCHBACK)).andReturn(0.0);
		
		/* Instruct the mock framework to get ready to run the test */
		replay(emissionDAO);
	}
	
	@After
	public void tearDown(){
		carbonMonoxideCalculator = null;
		emissionDAO = null; 
	}	

	@Test
	public void shouldCalculateSimpleNumber() {
		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 1);
		Assert.assertEquals(CAR_FORD_MONDEO_HATCHBACK_EMISSION, result, EPSILON);
		
		/* Verify that the mock framework has received all expected calls */
		verify(emissionDAO);
	}
	

}
