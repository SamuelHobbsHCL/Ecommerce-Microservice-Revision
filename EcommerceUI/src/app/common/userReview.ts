import { User } from "../user";
import { Product } from "./product";

export class userReview{
    id :number;
    user : User;
    product : Product;
	score : number;
	review : string;

    constructor () {
    }

}
