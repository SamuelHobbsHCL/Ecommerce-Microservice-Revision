import { User } from "../user";
import { Address } from "./address";

export class OrderDto{
    id :number;
    dtoStatus:string;
	shippingAddress :Address;
	billingAddress :Address;
	dtoUser : User;
	dtoCartItems :any;
	dtoDate : Date;
	dtoTotal :number;
    constructor () {}

}
