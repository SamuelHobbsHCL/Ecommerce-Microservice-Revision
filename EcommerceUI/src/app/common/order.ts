import { User } from "../user";

export interface Order {
    orderId:number;
    user:User;
    cartItems:any;
    orderDate:any;
    orderTotal:number;
    shippingAddressId:number;
    billingAddressId:number;
    orderStatus:string;

}