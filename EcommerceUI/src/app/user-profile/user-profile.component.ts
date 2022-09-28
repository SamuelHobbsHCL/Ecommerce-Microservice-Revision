import { Component, OnInit } from '@angular/core';
import Swal from 'sweetalert2';
import { AddressService } from '../service/address.service';
import { CartService } from '../service/cart.service';
import { UpdateService } from '../service/self-update.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

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

  public hasProfileImage() {

  }

  public hasCurrentOrder() {
    for(let order of this.orders) {
      if(order.orderStatus === "COMPLETED" || order.orderStatus === "SHIPPED") {
        return true;
      }
    }
    return false;
  }

  public userProfileUpdate(newUser: User){
    console.log(this.user.userId);

    this.service.updateSelf(this.user.userId, newUser).subscribe(
      (data) => {
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


  

