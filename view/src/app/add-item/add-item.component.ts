import { Component, OnInit, Input } from '@angular/core';
import { Item } from '../model/item.model';

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  @Input() item:Item;
  constructor() { }

  ngOnInit() {
  }

  setValue(value) {
    this.item[value.key] = value.value;
  }

}
