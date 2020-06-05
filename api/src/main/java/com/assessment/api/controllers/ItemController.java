package com.assessment.api.controllers;

import com.assessment.api.dtos.PriceCalculationRequestMultiple;
import com.assessment.api.dtos.PriceCalculationResponseDTO;
import com.assessment.api.exceptions.RecordNotFoundException;
import com.assessment.api.models.Item;
import com.assessment.api.models.TotalPrice;
import com.assessment.api.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/*
 * This class is the rest endpoints for resource "/items". All the CRUD operations and price calculations endpoints are listed here
 * @author Amila Viduranga
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService service;

    /*
     * This endpoint will return all the items that available in system.
     * @param no inputs needed
     * @return return available items array
     * @URL http://<host>:<port>/items/
     * @author Amila Viduranga
     */
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * This endpoint will return specific item that matches with the id
     * @param id id value of the item that is need to search
     * @return return response object of item that matches
     * @url http://<host>:<port>/items/{id}
     * @author Amila Viduranga
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Item entity = service.getItemById(id);
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * This endpoint will delete specific item that matches with the id
     * @param id id value of the item that is need to delete
     * @return  return status code of the item delete
     * @url http://<host>:<port>/items/{id}
     * @author Amila Viduranga
     */
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.FORBIDDEN;
    }

    /*
     * This endpoint will create / update item
     * @param item the item object that is need to crate or update
     * @return return created / updated item
     * @url http://<host>:<port>/items/
     * @author Amila Viduranga
     */
    @PostMapping
    public ResponseEntity<Item> createOrUpdateItem(@RequestBody Item item) throws RecordNotFoundException {
        Item updated = service.createOrUpdateItem(item);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * This endpoint will calculate price of a single item purchased
     * @param itemId id value of the item
     * @param amount amount that is purchased
     * @return return response of calculated price
     * @url http://<host>:<port>/items/calculate_price/single/{itemId}/{amount}
     * @author Amila Viduranga
     */
    @GetMapping("/calculate_price/single/{itemId}/{amount}")
    public ResponseEntity<PriceCalculationResponseDTO> calculateSingleItemPrice(@PathVariable("itemId") Long itemId, @PathVariable("amount") Integer amount) throws RecordNotFoundException {
        Item item = service.getItemById(itemId);
        PriceCalculationResponseDTO dto = new PriceCalculationResponseDTO();
        if(item != null) {
            dto.setPrice(service.calculateItemPrice(item, amount));
        }
        return new ResponseEntity<PriceCalculationResponseDTO>(dto, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * This endpoint will calculate price of a multiple items purchased
     * @param itemList array of priceCalculationRequestMultiple items
     * @return return response of calculated price
     * @url http://<host>:<port>/items/calculate_price/all
     * @author Amila Viduranga
     */
    @PostMapping("/calculate_price/all")
    public ResponseEntity<PriceCalculationResponseDTO> calculateTotalPrice(@RequestBody ArrayList<PriceCalculationRequestMultiple> itemsList) throws RecordNotFoundException {
        List<TotalPrice> items = new ArrayList<TotalPrice>();
        for(PriceCalculationRequestMultiple item: itemsList) {
            Item availableItem = service.getItemById(item.getItemId());
            if(availableItem != null) {
                TotalPrice newPrice = new TotalPrice();
                newPrice.setItem(availableItem);
                newPrice.setPurchasedAmount(item.getAmount());
                items.add(newPrice);
            }
        }
        PriceCalculationResponseDTO dto = new PriceCalculationResponseDTO();
        dto.setPrice(service.calculateTotalPrice(items));
        return new ResponseEntity<PriceCalculationResponseDTO>(dto, new HttpHeaders(), HttpStatus.OK);
    }
}
