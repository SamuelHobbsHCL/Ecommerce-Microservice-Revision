import { Component, OnInit } from '@angular/core';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  user = new User();

  constructor(private userService:UserService, private userAuthService: UserAuthService) { }

  ngOnInit(): void {
    this.userService.getCurrentUser()
    .subscribe((res: any)=>{
      this.user = res;
      console.log("User account found");
    });

  }

  public logout() {
    this.userAuthService.logout();
  }

}
