import { User } from "../user";
import { Product } from "./product";

export class userReviewDto{
    id :number;
    dtoUser : User;
    dtoProduct : Product;
	dtoScore : number;
	dtoReview : string;

    constructor () {
    }

}
