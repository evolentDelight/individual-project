package com.individual;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.ArrayList;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    //NOTE
    //For Conversion JUnit Testing, it is unnecessary to choose a Concrete Class: JSON, CSV, or XML
    //executeConversion is a separate step 

    //Conversion_ORIGINALCURRENCY_TARGETCURRENCY test
    @Test
    public void Conversion_USD_EUR()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "USD", "EUR");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 94.0, epsilon);
    }

    @Test
    public void Conversion_EUR_GBP()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "EUR", "GBP");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 86.0, epsilon);
    }

    @Test
    public void Conversion_GBP_INR()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "GBP", "INR");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 10398.0, epsilon);
    }

    @Test
    public void Conversion_AUD_CAD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "AUD", "CAD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 89.0, epsilon);
    }

    @Test
    public void Conversion_CAD_USD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "CAD", "USD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 73.0, epsilon);
    }

    @Test
    public void Conversion_CHF_AUD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "CHF", "AUD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 169.0, epsilon);
    }

    @Test
    public void Conversion_USD_CHF()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "USD", "CHF");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 91.0, epsilon);
    }

    @Test
    public void Conversion_JPY_USD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "JPY", "USD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 0.65, epsilon);
    }

    @Test
    public void Conversion_EUR_USD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(47, "EUR", "USD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 50.0, epsilon);
    }

    @Test
    public void Conversion_GBP_EUR()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(43, "GBP", "EUR");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 50.0, epsilon);
    }

    @Test
    public void Conversion_INR_GBP()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(10398, "INR", "GBP");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 100, epsilon);
    }

    @Test
    public void Conversion_CAD_AUD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(44.5, "CAD", "AUD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 50.0, epsilon);
    }

    @Test
    public void Conversion_USD_CAD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "USD", "CAD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 136.98630137, epsilon);
    }

    @Test
    public void Conversion_AUD_CHF()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(169, "AUD", "CHF");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 100, epsilon);
    }

    @Test
    public void Conversion_CHF_USD()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(100, "CHF", "USD");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 109.89010989, epsilon);
    }
    @Test
    public void Conversion_USD_JPY()
    {
        FileAndConversionTemplate program = new typeJSON("placeholder", "placeholder");
        Conversion test = new Conversion(150, "USD", "JPY");
        ArrayList<Conversion> list = new ArrayList<Conversion>();

        list.add(test);

        ArrayList <Conversion> convertedList = program.executeConversion(list);

        Conversion result = convertedList.get(0);

        double epsilon = 0.000001d;
        assertEquals(result.ConvertedAmount, 23076.9230769, epsilon);
    }
}
