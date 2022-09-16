import { Component, OnInit } from '@angular/core';
import { AddressService } from 'src/app/service/address.service';
import { UpdateService } from 'src/app/service/self-update.service';
import { UserService } from 'src/app/service/user.service';
import { User } from 'src/app/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  constructor(private updateService:UpdateService, private userService:UserService, private addressService:AddressService) { }

  user = new User();
  response : any;
  address: any;
  msg = '';

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
    
  }

  public userProfileUpdate(newUser: User){
    console.log(this.user.userId);

    this.updateService.updateSelf(this.user.userId, newUser).subscribe(
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
        Swal.fire(
          'Error!',
          'Please check your email or username!',
          'error'
        )
      }
    )
  }

}
