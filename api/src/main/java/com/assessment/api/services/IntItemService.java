package com.assessment.api.services;

import com.assessment.api.exceptions.RecordNotFoundException;
import com.assessment.api.models.Item;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IntItemService extends PriceCalculations{
    List<Item> getAllItems();
    Item getItemById(Long id) throws RecordNotFoundException;
    void deleteItemById(Long id) throws RecordNotFoundException;
    Item createOrUpdateItem(Item entity) throws RecordNotFoundException;
}
