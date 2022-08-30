import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, OktaAuth } from '@okta/okta-auth-js';
import { map } from 'jquery';
import { Observable } from 'rxjs';
import { filter } from 'rxjs/operators';
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

  constructor(private cartService : CartService, 
    private userAuthService: UserAuthService,
    private router: Router,
    public userService: UserService,
    private oktaAuthStateService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  ngOnInit(): void {
    const userRoles: any = JSON.stringify(this.userAuthService.getRoles());
    let isAdmin = false;
    for (let i = 0; i < userRoles.length; i++) {
      if( userRoles[i].roleName === "ADMIN") {
        isAdmin = true;
      }
    }
    console.log("isLoggedIn: " + this.isLoggedIn());
    console.log("isAdmin: " + isAdmin);

    if(this.isLoggedIn() && !isAdmin){
      this.cartService.getOrderByUserId().subscribe(data => {
        this.totalItem = data.cartItems.length;
      })
    }
  }
  
  public isLoggedIn() {
    return this.userAuthService.isLoggedIn() ;
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/']);
    window.location.reload();
  }

}