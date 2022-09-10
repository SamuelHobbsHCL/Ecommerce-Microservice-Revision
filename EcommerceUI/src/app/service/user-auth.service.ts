import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserAuthService {

  constructor() { }

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

}
