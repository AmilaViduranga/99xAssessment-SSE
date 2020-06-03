import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Item } from 'src/app/model/item.model';

@Component({
  selector: 'app-plus-minus-button',
  templateUrl: './plus-minus-button.component.html',
  styleUrls: ['./plus-minus-button.component.css']
})
export class PlusMinusButtonComponent implements OnInit {

  @Input() item: Item;
  @Input() isCarton: boolean = false;
  amount: number;

  @Output() getAmount: EventEmitter<any> = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  setAmountAdd() {
    if(this.amount == undefined || this.amount == 0) {
      alert("Please give amount greater than 0");
      return;
    }
    this.getAmount.emit({
      item: this.item,
      amount: this.amount,
      isCarton: this.isCarton,
      action: "add"
    })
  }

  setAmountReduce() {
    if(this.amount == undefined || this.amount == 0) {
      alert("Please give amount greater than 0");
      return;
    }
    this.getAmount.emit({
      item: this.item,
      amount: this.amount,
      isCarton: this.isCarton,
      action: "reduce"
    })
  }

}
