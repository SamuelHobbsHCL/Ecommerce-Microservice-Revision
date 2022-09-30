import { User } from "../user";
import { Product } from "./product";

export class userReview{
    id :number;
    dtoUser : User;
    dtoProduct : Product;
	dtoScore : number;
	dtoReview : string;

    constructor () {
    }

}
