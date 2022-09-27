import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { RegistrationService } from 'src/app/service/registration.service';
import { User } from 'src/app/user';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-user',
  templateUrl: './add-user.component.html',
  styleUrls: ['./add-user.component.css']
})
export class AddUserComponent implements OnInit {
  msg = '';
  registrationUser = new User();
  constructor(private router: Router, private _service: RegistrationService) { }

  ngOnInit(): void {
    //nothing to init
  }
  registerUser() {
    this._service.registerUser(this.registrationUser).subscribe(
      data => {
        console.log("Response received!");

        Swal.fire(
          'Completed',
          'User Successfully Created !',
          'success'
        ).then(() => {
          this.router.navigate(['/admin']).then(
            () => {
              window.location.reload();
            }
          );
        })
      },
      err => {
        console.log("Error!");
        Swal.fire(
          'Failure',
          'Error! Please check username information',
          'error'
        )
        this.msg = "User Account Creation successful!";
      }
    )

  }
}
