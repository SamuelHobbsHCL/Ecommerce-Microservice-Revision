import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';
import { ApiService } from '../service/api.service';
import { Location } from '@angular/common';
import { userReview } from '../common/userReview';
import { data } from 'jquery';
import { UserService } from '../service/user.service';
import { User } from '../user';
import { Product } from '../common/product';
import { UserReviewDto } from '../common/UserReviewDto';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
  id: any;
  private sub: any;
  product: any;
  newReview = new UserReviewDto;
  productId: any;
  userReviews: any[] = [];
  curUser: User;
  curProduct: Product;
  score: number;
  average: number;
  review: string;
  public form: FormGroup;
  alreadyReviewed: boolean = false;
  hasReviews: boolean = false;

  constructor(private fb: FormBuilder, private route: ActivatedRoute, private api: ApiService, private cartService: CartService, private _location: Location, private userService: UserService) {}

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id'];
      this.product = this.api.getProductById(this.id);
      this.productId = this.product;
    });

    this.api.getProductById(this.id).subscribe((data) => {
      this.product = data;

    }, (error: any) => {
      console.log("Unable to find product");
    });

    this.userService.getCurrentUser().subscribe((data) => {
      this.curUser = data;
    })

    this.api.getProductReviews(this.id).subscribe(data => {
      this.userReviews = data;
      this.userReviews.forEach((review) => {
        if(this.curUser.userId === review.user.userId) {
          this.alreadyReviewed = true;
        }
      })
 
      this.checkReview();
    }, (error: any) => {
      console.log("Unable to find product reviews");
    });

    this.api.getReviewAverage(this.id).subscribe(data => {
      this.average = Math.ceil(data);
    }, (error: any) => {
      console.log("Unable to find review average");
    });

    this.form = this.fb.group({
      rating1: ['', Validators.required]
    });
  }

  checkReview() {
    if(this.userReviews !== null && this.userReviews !== undefined && this.userReviews.length !== 0) {
      this.hasReviews = true
    } else {
      this.hasReviews = false;
    }
  }

  goBack() {
    this._location.back();
  }

  addtocart(item: any) {

    this.cartService.addtoCart(item, 1).subscribe(data => {
      Swal.fire(
        'Success!',
        'Product added to cart!',
        'success'
      ).then(function () {
        window.location.reload();
      })
    });

  }
  
  submitReview() {
    this.newReview.product = this.product;
    this.api.addUserReview(this.newReview).subscribe(data => {
      Swal.fire(
        'Completed!',
        'Review submitted!',
        'success'
      ).then(function () {
        window.location.reload();
      })
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }
}
