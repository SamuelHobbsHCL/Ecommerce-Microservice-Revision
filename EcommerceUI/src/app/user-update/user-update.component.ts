import { Component, OnInit } from '@angular/core';
import { UpdateService } from '../service/user-update.service';
import { User } from '../user';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {

  constructor(private service:UpdateService) { }

  user = new User();
  response : any;
  msg = '';

  ngOnInit(): void {
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

}
