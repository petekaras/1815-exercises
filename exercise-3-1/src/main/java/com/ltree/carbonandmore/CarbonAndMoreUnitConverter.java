package com.ltree.carbonandmore;

/**
 * Simple Convertor Utility class.
 * 
 * @author peter
 * 
 */
public class CarbonAndMoreUnitConverter implements UnitConverter {
	/**
	 * Constants that define the various ratios you need to calculate output.
	 */
	public static final double ONE_MILE_AS_KILOMETER = 1.609344;
	public static final double ONE_KILOGRAM_AS_POUNDS = 2.20462;
	@Override
	public double milesToKilometers(double miles) {
		//return miles * ONE_MILE_AS_KILOMETER;
		return 0;
	}
	@Override
	public double poundsToKilograms(double pounds) {
		//return (1 / ONE_KILOGRAM_AS_POUNDS) * pounds;
		return 0;
	}
	@Override
	public double kilometersToMiles(double kilometers) {
		//return (1 / ONE_MILE_AS_KILOMETER) * kilometers;
		return 0;
	}

	@Override
	public double kilogramsToPounds(double kilograms) {
		// TODO Auto-generated method stub
		return 0;
	}

}
