package com.ltree.carbonandmore;

/**
 * Simple Convertor Utility class.
 * 
 * @author peter
 *  
 */
public class BasicUnitConverter implements UnitConverter{
	
	
	/**
	 * Constants that define the various ratios you need to calculate output.
	 */
	public static final double ONE_MILE_AS_KILOMETER = 1.609344;
	public static final double ONE_KILOGRAM_AS_POUNDS = 2.20462;

	public double milesToKilometers(double miles) {
		return miles * ONE_MILE_AS_KILOMETER;
		
	}

	public double poundsToKilograms(double pounds) {
		return (1 / ONE_KILOGRAM_AS_POUNDS) * pounds;
		
	}

	public double kilometersToMiles(double kilometers) {
		return (1 / ONE_MILE_AS_KILOMETER) * kilometers;
		
	}

	@Override
	public double kilogramsToPounds(double pounds) {		
		return pounds * ONE_KILOGRAM_AS_POUNDS;
	}


}
