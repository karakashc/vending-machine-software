package com.techelevator;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CandyTest {

    private Candy candyTest;
    private Candy candyTest2 = new Candy("F7", "Taffy", new BigDecimal("1.25"), 7);

    @Before
    public void newCandy(){
        this.candyTest = new Candy("A1", "Snykkers", new BigDecimal("4.25"),5);
    }

    @Test
    public void testCandy(){
        String expected = "A1Snykkers4.255";
        String actual = candyTest.getItemCode() + candyTest.getItemName() + candyTest.getItemPrice() + candyTest.getItemQuantity();
        assertEquals(expected, actual);

    }

    @Test
    public void testCany2(){
        String expected = "F7Taffy1.257";
        String actual = candyTest2.getItemCode() + candyTest2.getItemName() + candyTest2.getItemPrice() + candyTest2.getItemQuantity();
        assertEquals(expected, actual);
    }

}