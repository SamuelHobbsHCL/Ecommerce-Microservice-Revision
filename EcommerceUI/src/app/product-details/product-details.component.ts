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
  userReviews: any;
  curUser: any;
  curProduct: Product;
  score: number;
  total = 0;
  review: string;


  average: string;
  count = 0;


  constructor(private route: ActivatedRoute, private api: ApiService, private cartService: CartService, private _location: Location, private userService: UserService) { }

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
    }

    );

    this.api.getProductReviews(this.id).subscribe(data => {
      this.userReviews = data;
      data.forEach(review => {
        this.count = this.count + 1;
        this.total = this.total + review.score;
      });
      this.average = (this.total / this.count).toFixed(2);
      console.log(this.average);
    }, (error: any) => {
      console.log("Unable to find product reviews");
    }

    );

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
  submitReview(userReviewDto) {
    this.newReview.product = this.product;
    console.log(JSON.stringify(this.newReview));
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
