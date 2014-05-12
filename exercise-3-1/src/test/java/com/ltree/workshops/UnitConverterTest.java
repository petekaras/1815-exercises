package com.ltree.workshops;

import org.junit.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class UnitConverterTest {
    private static final double MILES = 23.78;
    private static final double KILOMETERS = 38.27;
    private static final double KILOGRAMS = 455.30;
    private static final double POUNDS = 1003.76;
    /**
     * Epsilon is the value that the 2 numbers can be off by. This accounts for
     * rounding errors in the calculation. Note each unit has its different
     * degree of variance, so this is a nice way to express how accurate the
     * results are.
     */
    private static final double EPSILON_MILES = 0.01;
    private static final double EPSILON_KILOMETERS = 0.01;
    private static final double EPSILON_KILOGRAMS = 0.01;
    private static final double EPSILON_POUNDS = 0.01;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void shouldConvertBasicKilogramUnit() {
        double result = UnitConverter.kilogramsToPounds(1);
        Assert.assertEquals(UnitConverter.ONE_KILOGRAM_AS_POUNDS, result, EPSILON_POUNDS);
    }

    @Test
    public void shouldConvertBasicMileUnit() {
        double result = UnitConverter.milesToKilometers(1);
        Assert.assertEquals(UnitConverter.ONE_MILE_AS_KILOMETER, result, EPSILON_KILOMETERS);
    }

    @Test
    public void shouldConvertMilesToKilometers() {
        double result = UnitConverter.milesToKilometers(MILES);
        Assert.assertEquals(KILOMETERS, result, EPSILON_KILOMETERS);
    }

    @Test
    public void shouldConvertPoundsToKilograms() {
        double result = UnitConverter.poundsToKilograms(POUNDS);
        Assert.assertEquals(KILOGRAMS, result, EPSILON_KILOGRAMS);
    }

    @Test
    public void shouldConvertKilometersToMiles() {
        double result = UnitConverter.kilometersToMiles(KILOMETERS);
        Assert.assertEquals(MILES, result, EPSILON_MILES);
    }

    @Test
    public void shouldConvertKilogramsToPounds() {
        double result = UnitConverter.kilogramsToPounds(KILOGRAMS);
        Assert.assertEquals(POUNDS, result, EPSILON_POUNDS);
    }

}
