package com.techelevator;

import java.math.BigDecimal;

public class Drink extends VendingMachineItems{
    public Drink(String itemCode, String itemName, BigDecimal itemPrice, Integer itemQuantity) {
        super(itemCode, itemName, itemPrice, "Drink", itemQuantity);
    }
}
