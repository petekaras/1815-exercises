package com.ltree.carbonandmore;

import com.ltree.carbonandmore.dao.EmissionDAO;
import com.ltree.carbonandmore.dao.EmissionDAO.TransportType;

public class CarbonDioxideCalculator {
	EmissionDAO emissionDAO = null;

	/**
	 * Private constructor. This class will always need an emmissionDAO so
	 * Please use constructor with parameters.
	 */
	private CarbonDioxideCalculator() {
	}

	/**
	 * Public constructor.
	 * 
	 * @param emissionDAO
	 *            - the Data Access Object (DAO) that is responsible for getting
	 *            the data from the database.
	 */
	public CarbonDioxideCalculator(final EmissionDAO emissionDAO) {
		this.emissionDAO = emissionDAO;
	}

	/**
	 * Calculate the emission
	 * 
	 * @param transportType
	 *            - the type of transport
	 * @param distance
	 *            - the distance travelled in Kilometers
	 * @return - result in grams per km
	 */
	public double calculate(final TransportType transportType,
			final double distance) {
		double emission = emissionDAO.getEmission(transportType);//TODO: get the emission from the DAO 
		return (emission * distance);
	}
}
