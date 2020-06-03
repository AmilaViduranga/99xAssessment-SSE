import { Item } from './item.model';

export class Calculator {
    item: Item;
    amount: number;
    price: number;
}

export class TotalPriceRequest {
    itemId: number;
    amount: number;
}