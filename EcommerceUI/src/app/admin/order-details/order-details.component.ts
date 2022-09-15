import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/service/order.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  constructor(private orderService : OrderService, private _router: Router) { }

  public product: any;

  ngOnInit(): void {
    this.orderService.getOrderDetail()
    .subscribe(res => {
      this.product = res;
      console.log(this.product);
    });
  }



}
