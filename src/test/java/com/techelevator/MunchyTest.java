package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class MunchyTest {

    private Munchy munchyTest = new Munchy("A4", "Chippos", new BigDecimal("3.85"), 5);
    private Munchy munchyTest2 = new Munchy("K3", "Granola", new BigDecimal("3.56"), 6);

    @Test
    public void testMunchy(){
        String expected = "A4Chippos3.855";
        String actual = munchyTest.getItemCode() + munchyTest.getItemName() + munchyTest.getItemPrice() + munchyTest.getItemQuantity();
        assertEquals(expected, actual);
    }

    @Test
    public void testMunchy2(){
        String expected = "K3Granola3.566";
        String actual = munchyTest2.getItemCode() + munchyTest2.getItemName() + munchyTest2.getItemPrice() + munchyTest2.getItemQuantity();
        assertEquals(expected, actual);
    }
}