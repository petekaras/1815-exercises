package com.ltree.workshops;

import org.apache.log4j.Logger;

import com.ltree.workshops.dao.EmissionDAO;
import com.ltree.workshops.dao.EmissionDAO.TransportType;

public class CarbonDioxideCalculator {
	EmissionDAO emissionDAO = null;
	/* Workshop 3.3 */
	Logger logger = Logger.getLogger(CarbonDioxideCalculator.class.getName());
	
	/**
	 * Private constructor. This class will always need an emmissionDAO so
	 * Please use constructor with parameters.
	 */
	private CarbonDioxideCalculator() {
	}
	
	public CarbonDioxideCalculator(final EmissionDAO emissionDAO){
		this.emissionDAO = emissionDAO;
	}
	/**
	 * Calculate the emission
	 * @param transportType - the type of transport
	 * @param distance - the distance traveled in Kilometers
	 * @return
	 */
	public double calculate(final TransportType transportType,
			final double distance) {
		/* Workshop 3.3 */
		logger.info("Calculating transport type: " + transportType + " distance:" + distance);
		
		double emission = emissionDAO.getEmission(transportType);
		return (emission * distance);
	}
}
