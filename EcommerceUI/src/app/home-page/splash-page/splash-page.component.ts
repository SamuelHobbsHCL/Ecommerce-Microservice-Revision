import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserAuthService } from 'src/app/service/user-auth.service';

@Component({
  selector: 'app-splash-page',
  templateUrl: './splash-page.component.html',
  styleUrls: ['./splash-page.component.css']
})
export class SplashPageComponent implements OnInit {

  public isAuthenticated$!: Observable<boolean>;
  public isDatabaseLoggedIn : boolean;
  public isLoggedIn : boolean;
  public isAdmin : boolean;

  constructor(  
    private userAuthService: UserAuthService, 
   ) { }

  ngOnInit(): void {
    this.isAuthenticated$ = this.userAuthService.checkAuthenticated(); 
    this.isDatabaseLoggedIn = this.userAuthService.isDatabaseLoggedIn();
    this.isLoggedIn = this.userAuthService.isLoggedIn();
    this.isAdmin = this.userAuthService.isAdmin();
  }

  public logout() {
    this.userAuthService.logout();
  }

}
