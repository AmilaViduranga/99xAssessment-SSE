package com.assessment.api.services;

import com.assessment.api.models.Item;
import com.assessment.api.models.TotalPrice;
import com.assessment.api.repository.ItemRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemServiceTest {

    @MockBean
    private ItemRepository repository;

    @Autowired
    private ItemService service;

    @Test
    public void createNewItem() {
        Item item = getItem();
        Mockito.when(repository.save(item)).thenReturn(item);
        Item created = service.createOrUpdateItem(item);
        assertThat(created.getItemName()).isEqualTo(item.getItemName());
    }

    @Test
    public void updateExcistingItem() {
        Item item = getItem();
        String updateName = "updated Name";
        Mockito.when(repository.save(item)).thenReturn(item);
        Item created = service.createOrUpdateItem(item);
        created.setItemName(updateName);
        Mockito.when((repository.save(created))).thenReturn(created);
        Item updated = service.createOrUpdateItem(item);
        assertThat(updated.getItemName()).isEqualTo(updateName);
    }

    @Test
    public void searchItem() {
        Item item = getItem();
        Integer id = 1;
        item.setId(Long.parseLong(id.toString()));
        Optional<Item> itemExcisting = Optional.of(item);
        Mockito.when(repository.findById(Long.parseLong(id.toString()))).thenReturn(itemExcisting);
        Item getItem = service.getItemById(Long.parseLong(id.toString()));
        assertThat(getItem.getItemName()).isEqualTo(item.getItemName());
    }

    @Test
    public void calculatePriceOfSingleLessthanCartoonAmount() {
        Item item = getItem();
        Double singlePrice = service.calculateItemPrice(item, 10);
        assertThat(singlePrice).isEqualTo(113.75);
    }

    @Test
    public void calculatePriceOfSingleMorethanCartoonAmount() {
        Item item = getItem();
        Double singlePrice = service.calculateItemPrice(item, 30);
        assertThat(singlePrice).isEqualTo(288.75);
    }

    @Test
    public void calculatePriceOfMultipleItemsPurchased() {
        List<TotalPrice> purchasedItems = getItemListForCalculation();
        Double singlePrice = service.calculateTotalPrice(purchasedItems);
        assertThat(singlePrice).isEqualTo(14317.75);
    }

    private Item getItem() {
        Item item = new Item();
        item.setImageUrl("https://i.pinimg.com/originals/f2/99/69/f299690d8b53f5e038eb3d18ffdf3e7d.jpg");
        item.setPriceOFSingleCartoon(175.00);
        item.setNoOfUnitsInCartoon(20);
        item.setItemName("Test Penguin Ear");
        return item;
    }

    private List<TotalPrice> getItemListForCalculation() {
        Item item = new Item();
        item.setImageUrl("https://i.pinimg.com/originals/f2/99/69/f299690d8b53f5e038eb3d18ffdf3e7d.jpg");
        item.setPriceOFSingleCartoon(175.00);
        item.setNoOfUnitsInCartoon(20);
        item.setItemName("Test Penguin Ear");

        Item itemTwo = new Item();
        itemTwo.setImageUrl("https://i.pinimg.com/originals/f2/99/69/f299690d8b53f5e038eb3d18ffdf3e7d.jpg");
        itemTwo.setPriceOFSingleCartoon(825.00);
        itemTwo.setNoOfUnitsInCartoon(5);
        itemTwo.setItemName("Test Horse Shoe");

        TotalPrice priceOne = new TotalPrice();
        priceOne.setItem(item);
        priceOne.setPurchasedAmount(370);

        TotalPrice priceTwo = new TotalPrice();
        priceTwo.setItem(itemTwo);
        priceTwo.setPurchasedAmount(67);

        List<TotalPrice> priceList = new ArrayList<TotalPrice>();
        priceList.add(priceOne);
        priceList.add(priceTwo);

        return priceList;
    }
}
