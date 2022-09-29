import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-orders-list',
  templateUrl: './orders-list.component.html',
  styleUrls: ['./orders-list.component.css']
})
export class OrdersListComponent implements OnInit {

  public orderList:any;

  constructor(private adminService : AdminService, private _router: Router) { }

  ngOnInit(): void {
    this.adminService.getAllOrders()
    .subscribe(res => {
      this.orderList = res;
      console.log("Fetching order list");
    });
  }

  navigateToOrder(id : any) {
    this._router.navigate(['/orders-list', id])
  }
}
