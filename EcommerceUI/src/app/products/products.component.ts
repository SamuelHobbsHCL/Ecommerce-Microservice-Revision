import { Component, OnInit } from '@angular/core';
import { waitForAsync } from '@angular/core/testing';
import { ActivatedRoute, Router } from '@angular/router';
import { data } from 'jquery';
import { ApiService } from 'src/app/service/api.service';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';
import { Product } from '../common/product';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {
  public productList : Product[];
  isLoaded: boolean=false;
  searchStr: string = "";

  constructor(protected api : ApiService, private cartService : CartService, private _router : Router, private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.searchStr = params['search'];
    });
    console.log(this.searchStr);
  }
  
  ngOnInit(): void {
    // If searchStr is not present, search all products; else, search matching products
    if (this.searchStr ==  undefined) {
      this.api.getProduct()
      .subscribe(res=>{
        this.productList = res;
      });
    } else {
      // TODO Placeholder params for testing
      this.api.getSearchResult(this.searchStr,"0","10")
        .subscribe(res => {
          this.productList = res;
        });
    }
  }

  selectedProduct: any;

  goToProductDetails(product: any) {
    this._router.navigate(['/product-details', product.productId]);
  }

  onSelect(product: any): Promise<Product>{
      this.api.getProductById(product.productId).subscribe((data) => {
      this.selectedProduct = data;
      this.isLoaded = true;

    }, (error: any) => {
      console.log("Unable to find product");
    }
    
    );
    //console.log(this.selectedProduct);
    return this.selectedProduct;
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

  search(searchStr: string){
    console.log(searchStr);
    this._router.navigate(['/products'], { queryParams: { search: searchStr } });
    //this._router.navigate(['/products'], { queryParams: { search: searchStr } ,  queryParamsHandling: 'preserve' })
    //this.searchStr = searchStr;
    //window.location.reload();
  }
}