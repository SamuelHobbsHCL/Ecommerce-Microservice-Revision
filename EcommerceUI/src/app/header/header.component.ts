import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, OktaAuth } from '@okta/okta-auth-js';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CartService } from 'src/app/service/cart.service';
import { UserAuthService } from '../service/user-auth.service';
import { UserService } from '../service/user.service';


@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public totalItem : number = 0;

  public isAuthenticated$!: Observable<boolean>;
  public isDatabaseLoggedIn : boolean;
  public isLoggedIn : boolean;

  constructor(private cartService : CartService, 
    private userAuthService: UserAuthService) { }

  ngOnInit(): void {
    this.isAuthenticated$  = this.userAuthService.checkAuthenticated(); 
    this.isDatabaseLoggedIn = this.userAuthService.isDatabaseLoggedIn();
    this.isLoggedIn = this.userAuthService.isLoggedIn();

    const userRoles: any = JSON.stringify(this.userAuthService.getRoles());
    let isAdmin = false;
    for (let i = 0; i < userRoles.length; i++) {
      if( userRoles[i].roleName === "ADMIN") {
        isAdmin = true;
      }
    }
    console.log("isLoggedIn: " + this.isLoggedIn);
    console.log("isAdmin: " + isAdmin);
    console.log("okta login: " + this.isAuthenticated$)

    if(this.isLoggedIn && !isAdmin){
      this.cartService.getOrderByUserId().subscribe(data => {
        this.totalItem = data.cartItems.length;
      })
    }
  }

  public logout() {
    this.userAuthService.logout();
  }
 
}