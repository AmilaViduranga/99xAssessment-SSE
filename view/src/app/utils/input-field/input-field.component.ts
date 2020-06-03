import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-input-field',
  templateUrl: './input-field.component.html',
  styleUrls: ['./input-field.component.css']
})
export class InputFieldComponent implements OnInit {

  @Input() name: string;
  @Input() type: string = "text";
  @Input() label: string;
  @Input() id: string;
  @Input() describeId: string;
  @Input() placeHolder: string;
  @Input() model: any;
  @Input() description: string;
  @Input() isMandatory: boolean = false;

  @Output() getValue: EventEmitter<any> = new EventEmitter<any>();
  
  constructor() { }

  ngOnInit() {
  }

  sendValue() {
    this.getValue.emit({
      key: this.name,
      value: this.model
    })
  }

}
