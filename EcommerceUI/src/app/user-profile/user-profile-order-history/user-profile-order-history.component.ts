import { Component, OnInit } from '@angular/core';
import { AddressService } from 'src/app/service/address.service';
import { CartService } from 'src/app/service/cart.service';
import { UpdateService } from 'src/app/service/self-update.service';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-profile-order-history',
  templateUrl: './user-profile-order-history.component.html',
  styleUrls: ['./user-profile-order-history.component.css']
})
export class UserProfileOrderHistoryComponent implements OnInit {

  constructor(private service:UpdateService, private userService:UserService, private addressService:AddressService, private cartService:CartService) { }

  user = new User();
  response : any;
  address: any;
  msg = '';
  orders: any;

  ngOnInit(): void {
    this.userService.getCurrentUser()
    .subscribe((res: any)=>{
      this.user = res;
      console.log(this.user);
    });

    this.addressService.getUserAddress()
      .subscribe((res: any)=>{
        this.address = res;
        console.log(this.address);
      });
    
      this.cartService.getAllOrderForCurrentUser()
      .subscribe((res:any)=>{
        this.orders = res;
        console.log(this.orders);
      });
    
  }

  public hasCurrentOrder() {
    for(let order of this.orders) {
      if(order.orderStatus === "COMPLETED" || order.orderStatus === "SHIPPED") {
        return true;
      }
    }
    return false;
  }

  public hasPurchaseHistory() {
    for(let order of this.orders) {
      if(order.orderStatus === "DELIVERED") {
        return true;
      }
    }
    return false;
  }

  public userProfileUpdate(newUser: User){
    console.log(this.user.userId);

    this.service.updateSelf(this.user.userId, newUser).subscribe(
      data => {
        Swal.fire(
          'Success!',
          'Your profile has been updated!',
          'success'
        ).then(function(){
          window.location.reload();
        })
        console.log("Response received!");
        this.msg="Update successful!";
      },
      error => {
        console.log("Error!");
        this.msg = error.error;
      }
    )
  }
  

}
