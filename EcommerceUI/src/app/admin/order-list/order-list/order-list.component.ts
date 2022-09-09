import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.css']
})
export class OrderListComponent implements OnInit {

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
