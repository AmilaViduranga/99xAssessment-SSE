package com.assessment.api.services;

import com.assessment.api.models.Item;
import com.assessment.api.models.TotalPrice;

import java.util.List;

public interface PriceCalculations {
    Double calculateItemPrice(Item item, Integer amount);
    Double calculateTotalPrice(List<TotalPrice> list);
}
