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
      console.log(this.orderList);
    });
  }

  navigateToUsers() {
    this._router.navigate(['admin']);
  } 
  navigateToInventory() {
    this._router.navigate(['/inventory'])
  }
  navigateToOrder(order : any) {
    this._router.navigate(['/orders-list', order.orderId])
  }
}
