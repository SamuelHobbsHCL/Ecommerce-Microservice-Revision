import { HttpClient } from '@angular/common/http';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth, AuthState } from '@okta/okta-auth-js';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CartService } from 'src/app/service/cart.service';
import { UserAuthService } from 'src/app/service/user-auth.service';
import { UserService } from 'src/app/service/user.service';

@Component({
  selector: 'app-splash-page',
  templateUrl: './splash-page.component.html',
  styleUrls: ['./splash-page.component.css']
})
export class SplashPageComponent implements OnInit {

  public isAuthenticated$!: Observable<boolean>;
  public isDatabaseLoggedIn : boolean;
  public isLoggedIn : boolean;

  constructor(  
    private userAuthService: UserAuthService, 
   ) { }

  ngOnInit(): void {
    this.isAuthenticated$ = this.userAuthService.checkAuthenticated(this.isAuthenticated$); 
    this.isDatabaseLoggedIn = this.userAuthService.isDatabaseLoggedIn();
    this.isLoggedIn = this.userAuthService.isLoggedIn();
  }

  public logout() {
    this.userAuthService.logout();
  }

}
