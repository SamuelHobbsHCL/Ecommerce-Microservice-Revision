import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
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
        this.msg="Registration successful!";
        this._router.navigate(['/login']);
      },
      error => {
        console.log("Error!");
        this.msg = error.error;
      }
    )

  }
}
