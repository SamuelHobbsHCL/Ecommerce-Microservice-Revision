import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { RegistrationService } from '../service/registration.service';
import { User } from '../user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user = new User();
  msg='';

  constructor(private _service : RegistrationService, private _router: Router) { }

  ngOnInit(): void {
  }

  registerUser(){
    this._service.registerUser(this.user).subscribe(
      data => {
        console.log("Response received!");
       
        Swal.fire(
          'Success!',
          'User Successfully Registered !',
          'success'
        ).then(() =>{
          this._router.navigate(['/login']);
        })
      },
      error => {
        console.log("Error!");
        Swal.fire(
          'Error!',
          'Error! Please check username and/or email!',
          'error'
        )
        this.msg="Registration successful!";
        this._router.navigate(['/login']);
      }
    )

  }
}
