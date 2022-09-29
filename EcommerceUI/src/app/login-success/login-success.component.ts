import { Component, Inject, OnInit } from '@angular/core';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { AuthState, OktaAuth } from '@okta/okta-auth-js';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { UserAuthService } from '../service/user-auth.service';

@Component({
  selector: 'app-login-success',
  templateUrl: './login-success.component.html',
  styleUrls: ['./login-success.component.css']
})
export class LoginSuccessComponent implements OnInit {

  public name$!: Observable<string>;

  constructor(private _oktaAuthStateService: OktaAuthStateService,
    @Inject(OKTA_AUTH) private oktaAuth: OktaAuth,
    private userAuthService: UserAuthService) { }

  ngOnInit(): void {
    this.name$ = this._oktaAuthStateService.authState$.pipe(
      filter((authState: AuthState) => !!authState && !!authState.isAuthenticated),
      map((authState: AuthState) => authState.idToken?.claims.name ?? '')
    );

    let oktaToken = JSON.parse(localStorage.getItem('okta-token-storage')!);
    this.userAuthService.setRoles(oktaToken.accessToken.claims.groups);
    this.userAuthService.setToken(this.oktaAuth.getAccessToken()!);
    console.log("Saved token and roles to local storage");
  }

}
