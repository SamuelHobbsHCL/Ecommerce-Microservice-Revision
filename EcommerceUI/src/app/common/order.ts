

export interface Order {
    orderId:number;
    shippingAddressId:number;
    billingAddressId:number;
    user:any;
    orderItems: any;
    orderDate:any;
    orderTotal:number;
    orderStatus:string;

}