package com.ltree.carbonandmore.dao;

import java.math.BigDecimal;

public interface EmissionDAO {
	/**
	 * Enumeration of supported transport types held in the database. This class
	 * helps us limit what can be passed to our classes and so controls the
	 * behaviour of the system.
	 * 
	 * @author peter
	 * 
	 */
	public enum TransportType {
		CAR_FORD_MONDEO_HATCHBACK, CAR_FORD_GALAXY, CAR_NISSAN_MICRA, TRAIN_KIHA_281, TRAIN_E653, BICYCLE
	}

	/**
	 * Get the emission for a specific transport type.
	 * 
	 * @param transportType
	 *            - the transport type
	 * @return - the emission in grams per kilometer (g/km)
	 */
	public BigDecimal getEmission(final TransportType transportType);
	
	/**
	 * Sets the data source for this DAO
	 * @param datasource
	 */
	public void setDataSource(final String datasource);
}
