package com.ltree.workshop3_2;

import com.ltree.workshop3_2.dao.EmissionDAO;
import com.ltree.workshop3_2.dao.EmissionDAO.TransportType;

public class CarbonDioxideCalculator {
	EmissionDAO emissionDAO = null;

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
	 * @param distance - the distance travelled in Kilometers
	 * @return
	 */
	public double calculate(final TransportType transportType,
			final double distance) {
		double emission = emissionDAO.getEmission(transportType);
		return (emission * distance);
	}
}
