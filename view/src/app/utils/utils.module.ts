import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { InputFieldComponent } from './input-field/input-field.component';
import { PlusMinusButtonComponent } from './plus-minus-button/plus-minus-button.component';
import { ItemCardComponent } from './item-card/item-card.component';

@NgModule({
  declarations: [
    InputFieldComponent,
    PlusMinusButtonComponent,
    ItemCardComponent
  ],
  imports: [
    CommonModule,
    FormsModule
  ],
  exports: [
    InputFieldComponent,
    PlusMinusButtonComponent,
    ItemCardComponent
  ]
})
export class UtilsModule { }
