package com.assessment.api.dtos;

/*
 * description :-  This class is created to give support to serialize response that sent for item price calculation
 * developed by :- Amila Vidurange
 */
public class PriceCalculationResponseDTO {
    private Double price = 0.0;

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
