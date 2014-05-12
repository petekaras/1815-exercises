package com.ltree.workshops;
/**
 * Simple Convertor Utility class.
 * @author peter
 *
 */
public class UnitConverter {
    public static final double ONE_MILE_AS_KILOMETER = 1.609344;
    public static final double ONE_KILOGRAM_AS_POUNDS = 2.20462;
    
    public static double  milesToKilometers(double miles) {
        return miles * ONE_MILE_AS_KILOMETER;
    }
    public static double  poundsToKilograms(double pounds) {
        return (1/ONE_KILOGRAM_AS_POUNDS)*pounds;
    }   
    public static double  kilometersToMiles(double kilometers) {
        return (1/ONE_MILE_AS_KILOMETER)*kilometers;
    }   
    public static double  kilogramsToPounds(double kilograms) {
        return kilograms * ONE_KILOGRAM_AS_POUNDS;
    }      
}
