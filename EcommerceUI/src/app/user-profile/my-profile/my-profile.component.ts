import { Component, OnInit } from '@angular/core';
import { AddressDTO } from 'src/app/addressDTO';
import { AddressService } from 'src/app/service/address.service';
import { CloudinaryService } from 'src/app/service/cloudinary.service';
import { UpdateService } from 'src/app/service/self-update.service';
import { StateService } from 'src/app/service/state.service';
import { UserService } from 'src/app/service/user.service';
import { UpdateImageDTO } from 'src/app/UpdateImageDTO';
import { User } from 'src/app/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  constructor(private stateService:StateService, private updateService:UpdateService, private userService:UserService, private addressService:AddressService, private cloudinary: CloudinaryService) { }

  user = new User();
  response : any;
  address = new AddressDTO();
  newAddress = new AddressDTO();
  widget: any;
  updateImageDTO = new UpdateImageDTO();
  public states:any;

  ngOnInit(): void {
    this.userService.getCurrentUser()
    .subscribe((res: any)=>{
      this.user = res;
      console.log("Finding user");
    });

    this.addressService.getUserAddress()
      .subscribe((res: any)=>{
        this.setUpNewAddress(res);
        console.log("Finding address");
    });

    this.states=this.stateService.getStatesList();

    this.cloudinary.createUploadWidget(
      {
        cloudName: 'dwnb2nqcu',
        uploadPreset: 'ysvn2muf'
      },
      (error, result) => {
        if (!error && result && result.event === "success") {
          console.log("Uploading image....");
          this.updateImageDTO.imageUrl = result.info.url;
          this.userService.updateUserImage(this.updateImageDTO).subscribe(
            (data) => {
              
          }, (error) => {
            console.log("Done!");
            if(error == "OK") {
              Swal.fire(
                'Success!',
                'Your profile image has been updated!',
                'success'
              ).then(function(){
                window.location.reload();
              })
            } else {
              Swal.fire(
                'Error!',
                'Image upload error!',
                'error'
              )

              }
            }
            )
            
          }
        }
      ).subscribe(widget => this.widget = widget);
  }

  openWidget() {
    if (this.widget) {
      console.log('open')
      this.widget.open();
    }
  }

  deletePic() {
    this.updateImageDTO.imageUrl = "../../../assets/images/defaultProfileImage.png";
    this.userService.updateUserImage(this.updateImageDTO).subscribe(
      (data) => {
        }, (error) => {
      if(error == "OK") {
        Swal.fire(
          'Success!',
          'Your profile image has been deleted!',
          'success'
        ).then(function(){
          window.location.reload();
        })
      }else {
        console.log(error);
        Swal.fire(
          'Error!',
          'Image delete error!',
          'error'
        )

      }
    });
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

  public userProfileUpdate(newUser: User){

    this.updateService.updateSelf(this.user.userId, newUser).subscribe(
      (data) => {
        Swal.fire(
          'Success!',
          'Your profile has been updated!',
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
          'Please check your email or username!',
          'error'
        )
      }
    )
  }

  public userUpdateAddress(newAddress : AddressDTO){
    this.addressService.updateUserAddress(newAddress).subscribe(
      (data) => {
        Swal.fire(
          'Success!',
          'Your address has been updated!',
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
          'Please check your address!',
          'error'
        )
      }
    )
  }

}
