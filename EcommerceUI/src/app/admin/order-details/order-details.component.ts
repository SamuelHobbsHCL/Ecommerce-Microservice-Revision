import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AdminService } from 'src/app/service/admin.service';
import { OrderDto } from 'src/app/common/orderDto';
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
  orderUpdate = new OrderDto();

  constructor(private adminService : AdminService,private route: ActivatedRoute) { }

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

  onSelected(value:string): void {
		this.updateStatus = value;
	}

  updateOrder() {
    console.log(this.orderUpdate)
    this.setDto(this.order);
    if(this.updateStatus == null || this.updateStatus == this.order.orderStatus){
      Swal.fire(
        'Error!',
        'Please pick a valid order status',
        'error'
      )
    }else{
      this.adminService.updateOrder(this.id, this.orderUpdate).subscribe(data => {
        Swal.fire(
          'Success',
          'Order has been updated!',
          'success'
        )
        window.location.reload();
      })
    }
    
  }

  setDto(order: any): void{
    this.orderUpdate.dtoStatus = this.updateStatus; //status
    this.orderUpdate.billingAddress = this.order.billingAddress; //billing
    this.orderUpdate.shippingAddress = this.order.shippingAddress; //shipping
    this.orderUpdate.dtoDate = this.order.orderDate; //date
    this.orderUpdate.dtoTotal = this.order.orderTotal; //total
    this.orderUpdate.dtoUser = this.order.user; //user
    this.orderUpdate.id = this.order.orderId; //id
    this.orderUpdate.dtoCartItems = this.order.cartItems; //order items
  }
}
