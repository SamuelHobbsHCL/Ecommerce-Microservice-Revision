import { Component, OnInit } from '@angular/core';
import { AdminService } from 'src/app/service/admin.service';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  public userList : any;
  public admin : any;
  public isAdmin : boolean;

  constructor(private adminService : AdminService, private userService : UserService, private userAuthService: UserAuthService) { 

  }

  ngOnInit(): void {
    this.adminService.getAllUsers()
    .subscribe(res => {
      this.userList = res;
      console.log("User list found");
      console.log("Roles found");
    });
    this.isAdmin = this.userAuthService.isAdmin();
    this.userService.getCurrentUser()
    .subscribe(res => {
      this.admin = res;
      console.log(this.admin);
    });
  }

  public deleteUser(id : any) {
    console.log(id);
    this.adminService.deleteUser(parseInt(id)).subscribe(data => {
      window.location.reload();
    });
  }

  public ifdeleteUser(id : any) {
    console.log(id);
    var answer = window.confirm("Delete user?");
    if (answer) {
      this.adminService.deleteUser(parseInt(id)).subscribe(data => {
        window.location.reload();
      });
    }
    else {
      //some code
    }
  }

  public ifSame(id : any) {
    console.log(this.admin["userId"]);
    if(id != this.admin["userId"]){
      return true;
    }
    else{
      return false;
    }
  }
}
