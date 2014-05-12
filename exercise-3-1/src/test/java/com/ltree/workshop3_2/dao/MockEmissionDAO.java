package com.ltree.workshop3_2.dao;

public class MockEmissionDAO implements EmissionDAO{

	@Override
	public double getEmission(TransportType transportType) {
		switch (transportType) {
		case CAR_FORD_MONDEO_HATCHBACK:
			return 109;	
		case CAR_FORD_GALAXY:
			return 120;				
		default:
			return 0;
		}
	}

}
