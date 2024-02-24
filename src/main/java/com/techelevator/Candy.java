package com.techelevator;

import java.math.BigDecimal;

public class Candy extends VendingMachineItems{
    public Candy(String itemCode, String itemName, BigDecimal itemPrice, Integer itemQuantity) {
        super(itemCode, itemName, itemPrice, "Candy", itemQuantity);
    }
}
