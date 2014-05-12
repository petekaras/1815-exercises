package com.ltree.carbonandmore.dao;

import com.ltree.carbonandmore.dao.EmissionDAO;

public class MockEmissionDAO implements EmissionDAO{

	@Override
	public double getEmission(TransportType transportType) {
		switch (transportType) {
		case CAR_FORD_MONDEO_HATCHBACK:
			return 109;	
		//TODO: add some more fake data			
		default:
			return 0;
		}
	}

}
