package com.assessment.api.dtos;

/*
 * description :-  This class is created to give support to deserialize request that sent for multiple item calculation
 * developed by :- Amila Vidurange
 */
public class PriceCalculationRequestMultiple {
    private Long itemId;
    private Integer amount;

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
