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
  searchKey:string ="";
  
  constructor(private api : ApiService, private cartService : CartService, private _router : Router) { }
  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(): void {
    // If searchStr is not present, search all products; else, search matching products
    if (this.searchStr ==  undefined) {
      this.api.getProduct()
      .subscribe(res=>{
        this.productList = res;
      });
    } else {
      // TODO add pagination
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
    this.searchStr = searchStr;
    this.getProducts();
  }
}