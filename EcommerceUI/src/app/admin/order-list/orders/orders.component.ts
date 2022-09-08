import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';


@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  public orderList:any;

  constructor(private adminService : AdminService) { }

  ngOnInit(): void {
    this.adminService.getAllOrders()
    .subscribe(res => {
      this.orderList = res;
      console.log(this.orderList);
    });
  }

}
