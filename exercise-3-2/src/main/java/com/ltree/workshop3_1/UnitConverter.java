package com.ltree.workshop3_1;
/**
 * Interface defining the methods that a unit conversion class should implement.
 * Notice these are just definitions of methods.
 * If an Object "implements" this interface it must provide implementations for all these methods.
 * We'll see in later chapters how interfaces become very useful when "mocking" objects in our system.  
 * @author peter
 *
 */
public interface UnitConverter {
    public double  milesToKilometers(double miles);
    public double  poundsToKilograms(double pounds);    
    public double  kilometersToMiles(double kilometers);
    //TODO: provide a definition for a method that will return Pounds when passed kilograms as a parameter.  
    public double  kilogramsToPounds(double kilograms);

}
