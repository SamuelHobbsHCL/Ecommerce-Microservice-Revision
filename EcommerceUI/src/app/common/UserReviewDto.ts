import { User } from "../user";
import { Product } from "./product";

export class UserReviewDto{
    user : any;
    product : any;
	score : number;
	review : string;

    constructor (){
        this.product = null;
        this.score = 0;
        this.review = "";
    }

}
