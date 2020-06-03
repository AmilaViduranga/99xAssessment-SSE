import { Component, OnInit } from '@angular/core';
import { Item } from '../model/item.model';
import { BaseService } from '../base.service';
import { environment } from 'src/environments/environment';
import { Calculator, TotalPriceRequest } from '../model/calculator.model';
declare const $: any;

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  newItem: Item = new Item();
  avilableItems: Array<Item> = new Array<Item>();
  calculatedItemList: Array<Calculator> = new Array<Calculator>();
  totalPrice: number;

  constructor(private service: BaseService) { 

  }

  ngOnInit() {
    this.loadAvailableItems();
  }

  loadModalNewItem() {
    $('#newItem').modal("show");
  }

  loadAvailableItems() {
    this.avilableItems = [];
    this.service.get(environment.itemsApi, false).subscribe(response => {
      response.forEach(item => {
        let newItem = new Item();
        newItem.id = item.id;
        newItem.discountPrecentage = item.discountPrecentage;
        newItem.imageUrl = item.imageUrl;
        newItem.increasedPrecentage = item.increasedPrecentage;
        newItem.itemName = item.itemName;
        newItem.minCartoonAmountToDiscount = item.minCartoonAmountToDiscount;
        newItem.noOfUnitsInCartoon = item.noOfUnitsInCartoon;
        newItem.priceOFSingleCartoon = item.priceOFSingleCartoon;
        this.avilableItems.push(newItem);
      })
    })
  }

  passAmount(value) {
    if(value.isCarton) {
      value.amount = value.amount * value.item.noOfUnitsInCartoon;
    }
    let isPurchasedBefore: boolean = false;
    this.calculatedItemList.forEach(calculatedItem => {
      if(calculatedItem.item.id == value.item.id) {
        isPurchasedBefore = true;
        if(value.action == "add") {
          value.amount = calculatedItem.amount + value.amount;
        }
        if(value.action == "reduce") {
          value.amount = calculatedItem.amount - value.amount;
        }
        if(value.amount < 0) {
          value.amount = 0;
        }
      }
    })
    this.service.get(environment.itemsApi + "calculate_price/single/" + value.item.id + "/" + value.amount, false).subscribe(response => {
      if(isPurchasedBefore) {
        this.calculatedItemList.forEach(calculatedItem => {
          calculatedItem.amount = value.amount;
          calculatedItem.price = response.price;
        })
      } else {
        let newCalculatedItem = new Calculator();
        newCalculatedItem.item = value.item;
        newCalculatedItem.amount = value.amount;
        newCalculatedItem.price = response.price;
        this.calculatedItemList.push(newCalculatedItem);
      }
      // let requestBody = new Array<TotalPriceRequest>();
      // this.totalPrice = 0;
      // this.calculatedItemList.forEach(calculatedItem => {
      //   let request = new TotalPriceRequest();
      //   request.itemId = calculatedItem.item.id;
      //   request.amount = calculatedItem.amount;
      //   this.totalPrice = this.totalPrice + calculatedItem.price;
      //   requestBody.push(request);
      // })
      // this.service.post(environment.itemsApi + "calculate_price/all", false, requestBody).subscribe(responseTwo => {
      //   this.totalPrice = responseTwo.price;
      // })
    })
  }
}
