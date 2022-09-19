import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';
import { CartService } from 'src/app/service/cart.service';
import { OrderService } from 'src/app/service/order.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {
  orderStatus :any;
  orderText :any;
  id: string;
  private sub: any;
  order : any;

  constructor(private cartService : CartService, private adminService : AdminService, private _router: Router,private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.sub = this.route.params.subscribe(params => {
      this.id = params['id']; 
      this.order = this.adminService.getOrderById(this.id);
    });
    this.adminService.getOrderById(this.id).subscribe((data) => {
      this.order = data;

    }, (error: any) => {
      console.log("Unable to find the order");
    }
    
    );
    return this.order;
  }

  public updateOrder() {
    this.orderStatus = document.getElementById("orderStatus");
    this.orderText = this.orderStatus.options[this.orderStatus.selectedIndex].text;
    this.order.orderStatus = this.orderText;
    console.log(this.order);
    this.adminService.updateOrder(this.order).subscribe(data => {
      Swal.fire(
        'Success',
        'Order has been updated!',
        'success'
      )
      window.location.reload();
    })
  }


}
