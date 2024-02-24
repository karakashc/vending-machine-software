package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendingMachineCLITest {

    private VendingMachineCLI vendingMachineTest;

    @Before
    public void newVendingMachine(){
        this.vendingMachineTest = new VendingMachineCLI();
    }

    @Test
    public void testChange() {
        String expected = "Your change is 40 quarters, 0 dimes, and 0 nickels.";
        String actual = vendingMachineTest.getChange(new BigDecimal("10"));
        assertEquals(expected, actual);
    }

    @Test
    public void testChange2() {
        String expected = "Your change is 4 quarters, 1 dimes, and 1 nickels.";
        String actual = vendingMachineTest.getChange(new BigDecimal("1.15"));
        assertEquals(expected, actual);
    }

    @Test
    public void testChange3() {
        String expected = "Your change is 0 quarters, 0 dimes, and 0 nickels.";
        String actual = vendingMachineTest.getChange(new BigDecimal("0"));
        assertEquals(expected, actual);
    }

}