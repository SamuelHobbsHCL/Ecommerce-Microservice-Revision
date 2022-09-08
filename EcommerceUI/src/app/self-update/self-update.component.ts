import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { UpdateService } from '../service/self-update.service';
import { UserService } from '../service/user.service';
import { User } from '../user';

@Component({
  selector: 'app-self-update',
  templateUrl: './self-update.component.html',
  styleUrls: ['./self-update.component.css']
})
export class SelfUpdateComponent implements OnInit {

  constructor(private service:UpdateService, private userService:UserService) { }

  user = new User();
  response : any;
  msg = '';

  ngOnInit(): void {
    this.userService.getCurrentUser()
    .subscribe((res: any)=>{
      this.user = res;
      console.log(this.user);
    });
  }

  public get_Id(){
    var id = this.user.userid;
    return id;
  }

  public userSelfUpdate(id:any, user:User){
    this.service.updateSelf(parseInt(id),user).subscribe(
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

}
