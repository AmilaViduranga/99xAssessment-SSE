export class Item {
    id: number;
    itemName: string;
    noOfUnitsInCartoon: number;
    imageUrl: string;
    discountPrecentage: number = 0.1;
    increasedPrecentage: number = 0.3;
    minCartoonAmountToDiscount: number = 3;
    priceOFSingleCartoon: number;
}