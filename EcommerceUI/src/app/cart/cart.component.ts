import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CartService } from 'src/app/service/cart.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  public grandTotal !: number;
  order : any = null;
  orderItems: any[] = [];

  products: any[] = [];
  product : any = null;

  quantity: number = 0;

  constructor(private cartService : CartService, private router : Router) { }

  ngOnInit(): void {

    this.cartService.getOrderByUserId().subscribe(data => {
      this.order = data;
      this.orderItems = data.cartItems;
  
      console.log("Order and items found");
    })
  }

  addtocart(item: any){
    this.cartService.addtoCart(item, 1).subscribe(data => {
      Swal.fire(
        'Success!',
        'Product added to cart!',
        'success'
      ).then(() => {
        this.ngOnInit();
      })
    });
  }

  increaseQuantity(item: any) {
   console.log("Attempting quantity change");
   if(item.quantity < item.product.productStock) {
    this.cartService.addtoCart(item.product, 1).subscribe(data=> {
      this.ngOnInit();
    });
    } else {
      Swal.fire(
        'Attention!',
        'You have reached product stock limit!',
        'info'
      ).then(() =>{
        this.ngOnInit();
      })
    }
  }
  decreaseQuantity(item: any) {
    console.log("Lowering count...");
    if(item.quantity >1){
    this.cartService.addtoCart(item.product, -1).subscribe(data=> {
      this.ngOnInit();
   });
  }
  }

  removeItem(item: any){    
    console.log("clicked remove item");
    this.cartService.deleteOrderItemById(item).subscribe(data => {
      console.log("Attempting to remove item....");
      window.location.reload();

    });
  }
  emptycart(){
    console.log("clicked empty cart");
    this.cartService.deleteAllOrderItemsByOrder().subscribe(data => {
      console.log("Attemting to delete items");
      window.location.reload();
    });
  }
}
