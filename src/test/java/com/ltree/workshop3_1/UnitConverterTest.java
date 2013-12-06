package com.ltree.workshop3_1;


import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UnitConverterTest {
    private static final double MILES = 23.78;
    private static final double KILOMETERS = 38.27;
    private static final double KILOGRAMS = 455.30;
    private static final double POUNDS = 1003.78;
    
    private static final double ACCURACY = 0.01;
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldConvertMilesToKilometers() {
       double result = UnitConverter.milesToKilometers(MILES);
       Assert.assertEquals(KILOMETERS, result, ACCURACY);       
    }

    @Test
    public void shouldConvertPoundsToKilograms() {
        double result = UnitConverter.poundsToKilograms(POUNDS);
        Assert.assertEquals(KILOGRAMS, result,ACCURACY);
    }

    @Test
    public void shouldConvertKilometersToMiles() {
        double result = UnitConverter.kilometersToMiles(KILOMETERS);
        Assert.assertEquals(MILES, result, ACCURACY);     
    }

    @Test
    public void shouldConvertKilogramsToPounds() {
        double result = UnitConverter.kilogramsToPounds(POUNDS);
        Assert.assertEquals(POUNDS, result,KILOGRAMS);
    }

}
