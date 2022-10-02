import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';
import { ApiService } from '../service/api.service';
import {Location} from '@angular/common';
import { userReview } from '../common/userReview';
import { userReviewDto } from '../common/userReviewDto';
import { data } from 'jquery';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
  id: string;
  private sub: any;
  product: any = [];
  userReviews: userReview[];
  newReview: userReviewDto;
  curUser: any;

  constructor(private route: ActivatedRoute,private api : ApiService, private cartService : CartService, private _location: Location, private user: UserService) { }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id']; 
      this.product = this.api.getProductById(this.id);
    });
    this.api.getProductById(this.id).subscribe((data) => {
      this.product = data;

    }, (error: any) => {
      console.log("Unable to find product");
    }

    );

    this.api.getProductReviews(this.id).subscribe(data => {
      data.forEach(element => {
        console.log(element.score);
      });
      
    }, (error: any) => {
      console.log("Unable to find product reviews");
    }

    );

  }

  goBack(){
    this._location.back();
  }


  addtocart(item: any){
    
    this.cartService.addtoCart(item, 1).subscribe(data => {
      Swal.fire(
        'Success!',
        'Product added to cart!',
        'success'
      ).then(function(){
        window.location.reload();
      })
    });

   
  }

  submitReview(review){
    this.newReview.dtoProduct = this.product.productId;
    this.newReview.dtoScore = review.score;
    this.newReview.dtoReview = review.text;
    this.curUser = this.user.getCurrentUser;
    this.newReview.dtoUser = this.curUser;
    this.api.submitReview(this.newReview).subscribe(data =>{
      Swal.fire(
        'Completed!',
        'Review submitted!',
        'success'
      ).then(function(){
        window.location.reload();
      })
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
