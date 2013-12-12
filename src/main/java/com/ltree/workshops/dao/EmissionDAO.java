package com.ltree.workshops.dao;

public interface EmissionDAO {
	/**
	 * Enumeration of supported transport types held in the database.
	 * 
	 * @author peter
	 * 
	 */
	public enum TransportType {
		CAR_FORD_MONDEO_HATCHBACK, CAR_FORD_GALAXY, CAR_NISSAN_MICRA, TRAIN_KIHA_281, TRAIN_E653, BICYCLE
	}

	/**
	 * Gte the emission type
	 * 
	 * @param transportType
	 *            - the transport type
	 * @return - the emission in grams per kilometer (g/km)
	 */
	public double getEmission(final TransportType transportType);
}
