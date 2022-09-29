import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from '../service/order.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  public orderList : any;
  constructor(private order : OrderService, private _router : Router) { }
  ngOnInit(): void {
    this.order.getOrderDetail()
    .subscribe(res=>{
      this.orderList = res;
      console.log("Order list created")
    });
  }

}
