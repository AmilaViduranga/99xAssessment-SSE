import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Item } from 'src/app/model/item.model';

@Component({
  selector: 'app-item-card',
  templateUrl: './item-card.component.html',
  styleUrls: ['./item-card.component.css']
})
export class ItemCardComponent implements OnInit {

  @Input() item: Item;
  @Output() getAmount:EventEmitter<any> = new EventEmitter<any>();
  @Output() editItem: EventEmitter<any> = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  setAmount(value) {
    this.getAmount.emit(value);
  }

  isEdit() {
    this.editItem.emit({
      item: this.item
    })
  }

  isRemove() {

  }

}
