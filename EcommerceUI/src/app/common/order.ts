import { User } from "../user";

export interface Order {
    orderId:number;
    user:any;
    orderDate:any;
    orderTotal:number;
    shippingAddressId:number;
    billingAddressId:number;
    orderStatus:string;

}