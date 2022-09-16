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

  constructor(private cartService : CartService, 
    private http:HttpClient,
    private userService: UserService, 
    private userAuthService: UserAuthService, 
    private router : Router, 
    private oktaAuthStateService: OktaAuthStateService, 
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  ngOnInit(): void {
    this.checkAuthenticated(); 
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
