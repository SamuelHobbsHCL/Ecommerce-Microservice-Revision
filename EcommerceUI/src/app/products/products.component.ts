import { Component, OnInit } from '@angular/core';
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
  
  public productList : any;
  id : any;
  searchKey:string ="";
  constructor(private api : ApiService, private cartService : CartService) { }

  ngOnInit(): void {
    this.api.getProduct()
    .subscribe(res=>{
      this.productList = res;
      //console.log(this.productList)
    });
  }
  
  selectedProduct: any;
  testing : any;
  onSelect(product: any): void{
    this.selectedProduct = this.api.getProductById(product.productId);

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

}