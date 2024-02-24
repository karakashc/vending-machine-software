package com.techelevator;

import java.math.BigDecimal;

public class Munchy extends VendingMachineItems{
    public Munchy(String itemCode, String itemName, BigDecimal itemPrice, Integer itemQuantity) {
        super(itemCode, itemName, itemPrice, "Munchy", itemQuantity);
    }
}
