package com.assessment.api.services;

import com.assessment.api.exceptions.RecordNotFoundException;
import com.assessment.api.models.Item;
import com.assessment.api.models.TotalPrice;
import com.assessment.api.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This is service explanation that is use to handle all the business logic of price calculation
 * @author Amila Viduranga
 */
@Service
public class ItemService implements IntItemService{
    @Autowired
    ItemRepository repository;

    /*
     * calculate single item price that has purchased
     * @param item the item object that is needed to get calculation o price
     * @param amount the amount that customer purchased
     * @return calculated price of single item
     * @author Amila Viduranga
     */
    @Override
    public Double calculateItemPrice(Item item, Integer amount) {
        Double totalPrice = 0.0;
        try {
            Integer cartoonAmount = amount / item.getNoOfUnitsInCartoon();
            Integer remainingAmount = amount % item.getNoOfUnitsInCartoon();
            Double cartoonPrice = cartoonAmount * item.getPriceOFSingleCartoon();
            Double singleUnitPrice = remainingAmount * item.calculateSingleUnitPrice();
            totalPrice = cartoonPrice + singleUnitPrice;
            if(cartoonAmount >= item.getMinCartoonAmountToDiscount()) {
                Double discountAmount = (item.getPriceOFSingleCartoon() * item.getDiscountPrecentage());
                totalPrice = totalPrice - discountAmount;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalPrice;
    }

    /*
     * calculate once multiple items that has purchased
     * @param list list of items that has been purchased by the customer
     * @return calculated total value of multiple item price
     * @author Amila Viduranga
     */
    @Override
    public Double calculateTotalPrice(List<TotalPrice> list) {
        Double totalPrice = 0.0;
        for(TotalPrice purchasedItem: list) {
            totalPrice = totalPrice + this.calculateItemPrice(purchasedItem.getItem(), purchasedItem.getPurchasedAmount());
        }
        return totalPrice;
    }

    /*
     * get all the items that is available in the system
     * @return return all the items that is available in system
     * @author Amila Viduranga
     */
    public List<Item> getAllItems() {
        List<Item> itemList = repository.findAll();
        if(itemList.size() > 0) {
            return itemList;
        } else {
            return new ArrayList<Item>();
        }
    }

    /*
     * get single item by item id
     * @param id the id value of the item that is needed to get
     * @return return item that is match with given id
     * @author Amila Viduranga
     */
    public Item getItemById(Long id) throws RecordNotFoundException {
        Optional<Item> item = repository.findById(id);
        if(item.isPresent()) {
            return item.get();
        } else {
            throw new RecordNotFoundException("No item record exist for given id",id);
        }
    }

    /*
     * delete the item by given id
     * @param id id value of the item that is needed to delete
     * @author Amila Viduranga
     */
    public void deleteItemById(Long id) throws RecordNotFoundException {
        Optional<Item> item = repository.findById(id);

        if(item.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No item exist for given id",id);
        }
    }

    /*
     * create or update the item
     * @param entity the item that is need to create or update
     * @return return created / updated item
     * @author Amila Viduranga
     */
    public Item createOrUpdateItem(Item entity) throws RecordNotFoundException {
        if(entity.getId()!=null)  {
            Optional<Item> item = repository.findById(entity.getId());
            if(item.isPresent()) {
                Item newEntity = item.get();
                newEntity.setImageUrl(entity.getImageUrl());
                newEntity.setDiscountPrecentage(entity.getDiscountPrecentage());
                newEntity.setIncreasedPrecentage(entity.getIncreasedPrecentage());
                newEntity.setMinCartoonAmountToDiscount(entity.getMinCartoonAmountToDiscount());
                newEntity.setNoOfUnitsInCartoon(entity.getNoOfUnitsInCartoon());
                newEntity.setPriceOFSingleCartoon(entity.getPriceOFSingleCartoon());
                newEntity.setItemName(entity.getItemName());
                newEntity = repository.save(newEntity);
                return newEntity;
            } else {
                entity = repository.save(entity);
                return entity;
            }
        } else {
            entity = repository.save(entity);
            return entity;
        }
    }
}
