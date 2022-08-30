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

  constructor(private cartService : CartService, private router : Router) { }

  ngOnInit(): void {

    this.cartService.getOrderByUserId().subscribe(data => {
      this.order = data;
      this.orderItems = data.cartItems;
  
      console.log(this.order);
      console.log(this.orderItems);
    })
  }

  removeItem(item: any){    
    console.log("clicked remove item");
    this.cartService.deleteOrderItemById(item).subscribe(data => {
      console.log(data);
      window.location.reload();
    });
  }
  emptycart(){
    console.log("clicked empty cart");
    this.cartService.deleteAllOrderItemsByOrder().subscribe(data => {
      console.log(data);
      window.location.reload();
    });
  }

  checkout() {
    this.cartService.checkOut().subscribe(data => {
      console.log(data);
    })

    Swal.fire(
      'Success!',
      'You have successfully checked out!',
      'success'
    ).then(function(){
      window.location.reload();
    })

    this.router.navigate(['/']);

 

  }
}