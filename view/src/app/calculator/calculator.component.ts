import { Component, OnInit, Input } from '@angular/core';
import { Calculator } from '../model/calculator.model';

@Component({
  selector: 'app-calculator',
  templateUrl: './calculator.component.html',
  styleUrls: ['./calculator.component.css']
})
export class CalculatorComponent implements OnInit {

  @Input() calculatedITemList:Array<Calculator> = new Array<Calculator>();
  @Input() totalPrice: number = 0.0;

  constructor() { }

  ngOnInit() {
  }

}
