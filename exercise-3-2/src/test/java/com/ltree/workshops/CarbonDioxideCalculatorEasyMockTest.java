package com.ltree.workshops;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ltree.workshops.CarbonDioxideCalculator;
import com.ltree.workshops.dao.EmissionDAO;
import com.ltree.workshops.dao.EmissionDAO.TransportType;


public class CarbonDioxideCalculatorEasyMockTest {
	CarbonDioxideCalculator carbonMonoxideCalculator = null;
	EmissionDAO emissionDAO = null;
    private static final double EPSILON = 0.01;
    private static final double CAR_FORD_MONDEO_HATCHBACK_EMISSION = 109;
	
    @Before
	public void setUp(){	
		emissionDAO = createMock(EmissionDAO.class);	
		carbonMonoxideCalculator = new CarbonDioxideCalculator(emissionDAO);
		expect(emissionDAO.getEmission(TransportType.CAR_FORD_MONDEO_HATCHBACK)).andReturn(CAR_FORD_MONDEO_HATCHBACK_EMISSION);
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
		Assert.assertEquals(109, result, EPSILON);
		verify(emissionDAO);
	}
	@Test
	public void shouldCalculateOtherNumber() {
		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 1.46);
		Assert.assertEquals(159.14, result, EPSILON);
		verify(emissionDAO);		
	}	
	@Test
	public void shouldCalculateZeroCorrectly() {

		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 0);
		Assert.assertEquals(0, result, EPSILON);
		verify(emissionDAO);		
	}	

}
