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

  constructor(private cartService : CartService, 
    private http:HttpClient,
    private userService: UserService, 
    private userAuthService: UserAuthService, 
    private router : Router, 
    private oktaAuthStateService: OktaAuthStateService, 
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  ngOnInit(): void {
    this.checkAuthenticated(); 

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

  checkAuthenticated() {
    this.isAuthenticated$ = this.oktaAuthStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );
  }
  
  public isLoggedIn() {
    return this.userAuthService.isLoggedIn() ;
  }

  public isDatabaseLoggedIn() {
    if(this.userAuthService.getDatabaseLogin() === "true") {
      return true;
    } else {
      return false;
    }
  }

  public logout() {
    this.userAuthService.clear();
    this.router.navigate(['/']);
    window.location.reload();
  }
 
}