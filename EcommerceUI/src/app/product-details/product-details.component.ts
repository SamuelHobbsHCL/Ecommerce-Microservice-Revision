import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Product } from '../common/product';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';
import { ApiService } from '../service/api.service';

@Component({
  selector: 'app-product-details',
  templateUrl: './product-details.component.html',
  styleUrls: ['./product-details.component.css']
})
export class ProductDetailsComponent implements OnInit, OnDestroy {
  id: string;
  private sub: any;
  product: any = [];


  constructor(private route: ActivatedRoute,private api : ApiService, private cartService : CartService) { }

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
    //console.log(this.selectedProduct);
    return this.product;
  }


  addtocart(item: any){
    this.cartService.addtoCart(item, 1).subscribe(data => {
      //console.log(data);
      Swal.fire(
        'Success!',
        'Product added to cart!',
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
