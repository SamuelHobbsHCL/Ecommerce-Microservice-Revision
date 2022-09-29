import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { ConfirmedValidator } from 'src/app/confirmed.validator';
import { PasswordDTO } from 'src/app/passwordDTO';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-user-profile-security',
  templateUrl: './user-profile-security.component.html',
  styleUrls: ['./user-profile-security.component.css']
})
export class UserProfileSecurityComponent implements OnInit {
  
  form: FormGroup = new FormGroup({});
  passwordDTO = new PasswordDTO();
  public isAuthenticated$!: Observable<boolean>;


  constructor(private userService: UserService, private fb: FormBuilder, private router: Router, private userAuthService: UserAuthService) {
    this.isAuthenticated$ = this.userAuthService.checkAuthenticated();

    this.form = fb.group({
      current_password: ['', [Validators.required]],
      password: ['', [Validators.required]],
      confirm_password: ['', [Validators.required]]
    }, { 
      validator: ConfirmedValidator('password', 'confirm_password')
    })
   }
   
  ngOnInit(): void {
  }

  get f(){
    return this.form.controls;
  }
   
  submit(){
    console.log("Form submitted");
    this.userUpdatePassword();

  }

  public deleteCurrentUser(){
    this.userService.deleteCurrentUser().subscribe(
      (data) => {
        Swal.fire(
          'Success!',
          'Your account has been deleted. Hope to see you again!',
          'success'
        ).then(() => {
          this.userAuthService.clear();
          this.router.navigate(['/home-page']);
          
        })
      }, (error) => {
        console.log("Error here");
        console.log(error);
        Swal.fire(
          'Success!',
          'Your account has been deleted. Hope to see you again!',
          'success'
        ).then(() => {
          this.userAuthService.clear();
          this.router.navigate(['/home-page']);
          
        })
      }
    );
  }

  public userUpdatePassword(){

    this.userService.updatePassword(this.passwordDTO).subscribe(
      (data) => {

      }, (error) => {
        console.log(error);
        if(error == "OK") {
        Swal.fire(
          'Success!',
          'Your password has been updated!',
          'success'
        ).then(function(){
          window.location.reload();
        })
      } else {
        Swal.fire(
          'Error!',
          'Please check your current password!',
          'error'
        )
      }
    })
  }
}
