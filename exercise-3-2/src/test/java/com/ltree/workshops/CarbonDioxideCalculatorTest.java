package com.ltree.workshops;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import com.ltree.workshops.CarbonDioxideCalculator;
import com.ltree.workshops.dao.EmissionDAO;
import com.ltree.workshops.dao.MockEmissionDAO;
import com.ltree.workshops.dao.EmissionDAO.TransportType;

public class CarbonDioxideCalculatorTest {
	CarbonDioxideCalculator carbonMonoxideCalculator = null;
	EmissionDAO emissionDAO = new MockEmissionDAO();
    private static final double EPSILON = 0.01;
	
    @Before
	public void setUp(){		
		carbonMonoxideCalculator = new CarbonDioxideCalculator(emissionDAO);
	}
	
	@After
	public void tearDown(){
		carbonMonoxideCalculator = null;
	}	

	@Test
	public void shouldCalculateSimpleNumber() {
		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 1);
		Assert.assertEquals(109, result, EPSILON);
	}
	@Test
	public void shouldCalculateOtherNumber() {
		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 1.46);
		Assert.assertEquals(159.14, result, EPSILON);
	}	
	@Test
	public void shouldCalculateZeroCorrectly() {
		double result = carbonMonoxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 0);
		Assert.assertEquals(0, result, EPSILON);
	}	
	/* Workshop 3.3 */
	@Test (expected = IllegalArgumentException.class) 
	public void shouldNotAcceptTransportType() {
		carbonMonoxideCalculator.calculate(TransportType.BICYCLE, 1);
	}	
	

}
