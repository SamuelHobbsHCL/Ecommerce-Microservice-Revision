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
  updateStatus :any;
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
    console.log(this.order);
    return this.order;
  }

  onSelected(value:string): void {
		this.updateStatus = value;
	}

  public updateOrder() {
    this.order.orderStatus = this.updateStatus;
    this.adminService.updateOrder(this.id, this.order).subscribe(data => {
      Swal.fire(
        'Success',
        'Order has been updated!',
        'success'
      )
      window.location.reload();
    })
  }


}
