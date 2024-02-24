package com.techelevator;

import java.math.BigDecimal;

public abstract class VendingMachineItems {

    String itemCode;

    String itemName;

    BigDecimal itemPrice;

    String itemCategory;

    int itemQuantity = 5;



    public VendingMachineItems(String itemCode, String itemName, BigDecimal itemPrice, String itemCategory, Integer itemQuantity) {
        this.itemCode = itemCode;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCategory = itemCategory;
        this.itemQuantity = itemQuantity;
    }

    public final String getItemCode() {
        return itemCode;
    }

    public final void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public final String getItemName() {
        return itemName;
    }

    public final void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public final BigDecimal getItemPrice() {
        return itemPrice;
    }

    public final void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public final String getItemCategory() {
        return itemCategory;
    }

    public final void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public final int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    @Override
    public String toString() {
        return "Code: " + itemCode + " | " + "Name: " + itemName + " | " + "Price: " + itemPrice + " | " + "Quantity: " + itemQuantity;
    }

    public void decreaseQuantity() {
        itemQuantity--;
    }
}


