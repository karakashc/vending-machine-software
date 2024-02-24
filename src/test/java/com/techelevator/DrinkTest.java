package com.techelevator;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DrinkTest {

    private Drink drinkTest = new Drink("A2", "Ginger Ayle", new BigDecimal("1.85"), 5);
    private Drink drinkTest2 = new Drink("V1", "Sipps", new BigDecimal("23.54"), 31);

    @Test
    public void testDrink(){
        String expected = "A2Ginger Ayle1.855";
        String actual = drinkTest.getItemCode() + drinkTest.getItemName() + drinkTest.getItemPrice() + drinkTest.getItemQuantity();
        assertEquals(expected, actual);
    }

    @Test
    public void testDrink2(){
        String expected = "V1Sipps23.5431";
        String actual = drinkTest2.getItemCode() + drinkTest2.getItemName() + drinkTest2.getItemPrice() + drinkTest2.getItemQuantity();
        assertEquals(expected, actual);
    }

}