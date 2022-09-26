import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { AddressDTO } from '../addressDTO';
import { AddressService } from '../service/address.service';
import { UpdateService } from '../service/user-update.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  [x: string]: any;
  constructor(private updateService:UpdateService, private userService:UserService, private addressService:AddressService, private activatedRoute : ActivatedRoute) { }

  id : number;
  user = new User();
  response : any;
  address = new AddressDTO();
  newAddress = new AddressDTO();
  msg = '';

  ngOnInit(): void {
    let userid = this.activatedRoute.snapshot.params["userid"];
    console.log("userid " + userid);
    this.id = userid;
    console.log(this.id);
    console.log(this.user);

    this.userService.getUserById(this.id)
    .subscribe((data)=>{
      this.user = data;
      console.log("response" + data);
    });

    this.addressService.getAddressById(this.id)
    .subscribe((res: any)=>{
      this.setUpNewAddress(res);
    });

    
    
  }

  public getUrl(){
    var pathArray = window.location.pathname.split('/');
    var secondLevelLocation = pathArray[2];
    return secondLevelLocation;
  }

  public userUpdate(id:any, user:User){
    this.service.updateUser(parseInt(id),user).subscribe(
      data => {
        console.log("Response received!");
        this.msg="Update successful!";
      },
      error => {
        console.log("Error!");
        this.msg = error.error;
      }
    )
  }

  setUpNewAddress(res: any) {
    if (res === null) { /* If user doesn't have address */
      this.address.city =  "";
      this.address.street = "";
      this.address.state = "";
      this.address.zipcode = "";
      this.address.country = "";
    } else {
      this.address = res;
    }
    this.newAddress.city = this.address.city;
    this.newAddress.unit = this.address.unit;
    this.newAddress.street = this.address.street;
    this.newAddress.state = this.address.state;
    this.newAddress.zipcode = this.address.zipcode;
    this.newAddress.country = this.address.country;
  }

  public userProfileUpdate(user: User){
    console.log(this.user.userId);

    this.updateService.updateUser(this.user.userId, user).subscribe(
      (data) => {
        Swal.fire(
          'Success!',
          'Profile has been updated!',
          'success'
        ).then(function(){
          window.location.reload();
        })
        console.log("Response received!");
      },
      (error) => {
        console.log("Error!");
        Swal.fire(
          'Error!',
          'Please check email or username!',
          'error'
        )
      }
    )
  }

  // Vague which is which?
  public userUpdateAddress(newAddress : AddressDTO){
    console.log(this.newAddress);
    this.addressService.updateAddressById(this.id, newAddress).subscribe(
      (data) => {
        Swal.fire(
          'Success!',
          'Address has been updated!',
          'success'
        ).then(function(){
          window.location.reload();
        })
        console.log("Response received!");
      },
      (error) => {
        console.log("Error!");
        Swal.fire(
          'Error!',
          'Please check address!',
          'error'
        )
      }
    )
  }

}
