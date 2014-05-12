package com.ltree.carbonandmore;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import com.ltree.carbonandmore.CarbonDioxideCalculator;
import com.ltree.carbonandmore.dao.EmissionDAO;
import com.ltree.carbonandmore.dao.MockEmissionDAO;
import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;

public class CarbonDioxideCalculatorTest {
	CarbonDioxideCalculator carbonDioxideCalculator = null;
	
    private static final double EPSILON = 0.01;
	
    @Before
	public void setUp(){		
    	EmissionDAO mockEmissionDAO = new MockEmissionDAO();
    	//TODO: create an instance of the CarbonDioxideCalculator and pass in the mockEmissionDAO.
    	carbonDioxideCalculator = null;//new CarbonDioxideCalculator(mockEmissionDAO);
	}
	
	@After
	public void tearDown(){
		carbonDioxideCalculator = null;
	}	

	@Test
	public void shouldCalculateSimpleNumber() {
		double result = carbonDioxideCalculator.calculate(TransportType.CAR_FORD_MONDEO_HATCHBACK, 1);
		Assert.assertEquals(109, result, EPSILON);
	}
	

}
