package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class GumTest {

    private Gum gumTest = new Gum("A1", "U-Chews", new BigDecimal("1.65"), 5);
    private Gum gumTest2 = new Gum("P0", "Little Purple", new BigDecimal("0.89"), 0);

    @Test
    public void testGum(){
        String expected = "A1U-Chews1.655";
        String actual = gumTest.getItemCode() + gumTest.getItemName() + gumTest.getItemPrice() + gumTest.getItemQuantity();
        assertEquals(expected, actual);
    }

    @Test
    public void testGum2(){
        String expected = "P0Little Purple0.890";
        String actual = gumTest2.getItemCode() + gumTest2.getItemName() + gumTest2.getItemPrice() + gumTest2.getItemQuantity();
        assertEquals(expected, actual);
    }

}