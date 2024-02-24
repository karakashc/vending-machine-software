package com.techelevator;

import java.math.BigDecimal;

public class Gum extends VendingMachineItems{
    public Gum(String itemCode, String itemName, BigDecimal itemPrice, Integer itemQuantity) {
        super(itemCode, itemName, itemPrice, "Gum", itemQuantity);
    }
}
