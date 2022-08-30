import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  public productList : any;
  searchKey:string ="";
  constructor(private api : ApiService, private cartService : CartService) { }

  ngOnInit(): void {
    this.api.getProduct()
    .subscribe(res=>{
      this.productList = res;
      //console.log(this.productList)
    });
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