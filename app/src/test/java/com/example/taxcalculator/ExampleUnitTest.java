package com.example.taxcalculator;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() {
        assertEquals(5, 2 + 2);
    }

    Tax tax = new Tax(1234,2021);
    @Test
    public void Tax_is_null() {

        assertTrue(tax.getCapital() != 0);
        assertTrue(tax.getAnnual() != 0);
        assertTrue(tax.getEligibleDividends() != 0);
        assertTrue(tax.getInEligibleDividends() != 0);
        assertEquals(0, tax.calculateTax(), 0.0);
        assertTrue(tax.getOther() != 0);
        assertEquals(2121, tax.getYear());
    }


    @Test
    public void Tax_is_calulated() {
        assertTrue(tax.calculateTax() != 0);

    }
}