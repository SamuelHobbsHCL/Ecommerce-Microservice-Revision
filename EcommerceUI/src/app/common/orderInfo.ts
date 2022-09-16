import { Address } from "./address";

export class OrderInfo {
    shippingAddress: Address;
    billingAddress: Address;

    constructor(){
        this.shippingAddress = new Address();
        this.billingAddress = new Address();
    }
}