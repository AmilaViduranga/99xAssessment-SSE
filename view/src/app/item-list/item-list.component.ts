import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Item } from '../model/item.model';

@Component({
  selector: 'app-item-list',
  templateUrl: './item-list.component.html',
  styleUrls: ['./item-list.component.css']
})
export class ItemListComponent implements OnInit {

  @Input() items: Array<Item>;
  @Output() getAmount: EventEmitter<any> = new EventEmitter<any>();
  @Output() getEditItem: EventEmitter<any> = new EventEmitter<any>();
  constructor() { }

  ngOnInit() {
  }

  setAmount(value) {
    this.getAmount.emit(value);
  }

  passEditItem(value) {
    this.getEditItem.emit(value);
  }

}
