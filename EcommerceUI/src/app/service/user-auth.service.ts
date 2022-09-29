import { Inject, Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { OktaAuth, AuthState } from '@okta/okta-auth-js';
import { filter, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor(
    private router : Router, 
    private oktaAuthStateService: OktaAuthStateService, 
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth) { }

  public setRoles(roles:[]) {
    localStorage.setItem('roles', JSON.stringify(roles));
  }

  public getRoles() : [] {
    return JSON.parse(localStorage.getItem('roles') as string) ;
  }

  public setToken(jwtToken : string) {
    localStorage.setItem('jwtToken', jwtToken);
  }

  public getToken() : string {
    return localStorage.getItem('jwtToken') as string;
  }

  public clear() {
    localStorage.clear();
  }

  public isLoggedIn() : boolean {
    if(this.getRoles() && this.getToken()) {
      return true;
    } else {
      return false;
    }
  }

  public setDatabaseLogin(databaseLogin : string) {
    localStorage.setItem('databaseLogin', databaseLogin);
  }
  
  public getDatabaseLogin() : string {
    return localStorage.getItem('databaseLogin') as string;
  }

  public checkAuthenticated() {
    let isAuthenticated$ = this.oktaAuthStateService.authState$.pipe(
      filter((s: AuthState) => !!s),
      map((s: AuthState) => s.isAuthenticated ?? false)
    );

    return isAuthenticated$;
  }

  public isDatabaseLoggedIn() {
    if(this.getDatabaseLogin() === "true") {
      return true;
    } else {
      return false;
    }
  }

  public isAdmin(){
    //console.log(this.getRoles());
    var x =0;
    if(this.getRoles() !== null) {
    this.getRoles().forEach(function(item){
      if(item['roleName']==='ADMIN'){
        console.log("IS an ADMIN");
        x=1;
      }
    });
    if(x==1){
      return true;
    }
    else{
      return false;
    }} else {
      return false;
    }
  }

  public logout() {
    this.clear();
    this.router.navigate(['/']).then(() => {
      window.location.reload();
    });
  }
}
