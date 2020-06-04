package com.assessment.api.models;

/*
 * description :-  This class is created to give support to deserialize request that sent for multiple item calculation
 * developed by :- Amila Vidurange
 */
public class TotalPrice {
    private Item item;
    private Integer purchasedAmount;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getPurchasedAmount() {
        return purchasedAmount;
    }

    public void setPurchasedAmount(Integer purchasedAmount) {
        this.purchasedAmount = purchasedAmount;
    }
}
