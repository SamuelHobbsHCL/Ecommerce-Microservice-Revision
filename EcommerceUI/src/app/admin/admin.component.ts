import { Component, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  user = new User();
  hasProfileImage = false;

  constructor(private userService:UserService) { }

  ngOnInit(): void {
    this.userService.getCurrentUser()
    .subscribe((res: any)=>{
      this.user = res;
      console.log(this.user);
    });

    if(this.user.profileImage !== null || this.user.profileImage !== '') {
      this.hasProfileImage = true;
    } else {
      this.hasProfileImage = false;
    }
  }

}
