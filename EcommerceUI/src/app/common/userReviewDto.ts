import { User } from "../user";
import { Product } from "./product";

export class userReviewDto{
    id :number;
    dtoUser : User;
    dtoProduct : any;
	dtoScore : number;
	dtoReview : string;

    constructor () {
    }

}
