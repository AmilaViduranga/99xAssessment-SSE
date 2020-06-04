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
 * description :- This class is the rest endpoints for resource "/items". All the CRUD operations and price calculations endpoints are listed here
 * develop by: Amila Viduranga
 */
@RestController
@RequestMapping("/items")
public class ItemController {
    @Autowired
    ItemService service;

    /*
     * description :- This endpoint will return all the items that available in system.
     * input:- no inputs needed
     * output:- return available items array
     * url :- http://<host>:<port>/items/
     * develop by: Amila Viduranga
     */
    @GetMapping
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> list = service.getAllItems();
        return new ResponseEntity<List<Item>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * description :- This endpoint will return specific item that matches with the id
     * input:- need to pass id value as url parameter
     * output:- return response object of item that matches
     * url :- http://<host>:<port>/items/{id}
     * develop by: Amila Viduranga
     */
    @GetMapping("/{id}")
    public ResponseEntity<Item> getItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        Item entity = service.getItemById(id);
        return new ResponseEntity<Item>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * description :- This endpoint will delete specific item that matches with the id
     * input:- need to pass id value as url parameter
     * output:- return status code of the item delete
     * url :- http://<host>:<port>/items/{id}
     * develop by: Amila Viduranga
     */
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id) throws RecordNotFoundException {
        service.deleteItemById(id);
        return HttpStatus.FORBIDDEN;
    }

    /*
     * description :- This endpoint will create / update item
     * input:- need to pass item object value in request body
     * output:- return created / updated item
     * url :- http://<host>:<port>/items/
     * develop by: Amila Viduranga
     */
    @PostMapping
    public ResponseEntity<Item> createOrUpdateItem(@RequestBody Item item) throws RecordNotFoundException {
        Item updated = service.createOrUpdateItem(item);
        return new ResponseEntity<Item>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    /*
     * description :- This endpoint will calculate price of a single item purchased
     * input:- need to pass item id and purchased amount as url params
     * output:- return response of calculated price
     * url :- http://<host>:<port>/items/calculate_price/single/{itemId}/{amount}
     * develop by: Amila Viduranga
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
     * description :- This endpoint will calculate price of a multiple items purchased
     * input:- need to pass item id and purchased amount in wraped array in request body
     * output:- return response of calculated price
     * url :- http://<host>:<port>/items/calculate_price/all
     * develop by: Amila Viduranga
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
