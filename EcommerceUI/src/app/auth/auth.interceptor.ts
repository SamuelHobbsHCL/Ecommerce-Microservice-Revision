import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Inject, Injectable } from "@angular/core";
import { Router } from "@angular/router";
import { OKTA_AUTH } from "@okta/okta-angular";
import { OktaAuth } from "@okta/okta-auth-js";
import { Observable, throwError } from "rxjs";
import { catchError } from "rxjs/operators";
import { UserAuthService } from "../service/user-auth.service";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private userAuthService : UserAuthService, private router : Router, @Inject(OKTA_AUTH) private _oktaAuth: OktaAuth) {}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if(req.headers.get('No-Auth') === 'True') {
            return next.handle(req.clone());
        }

        const backendToken = this.userAuthService.getToken();
        const oktaToken = this._oktaAuth.getAccessToken();

        if(backendToken != null) {
            req = this.addBackEndToken(req, backendToken);
        } else if (oktaToken != null) {
            req = this.addOktaToken(req, oktaToken);
        }

        return next.handle(req).pipe(
            catchError(
                (err: HttpErrorResponse) => {
                    console.log(err.status);
                    if(err.status === 401) {
                        this.router.navigate(['/auth/login']);
                    } else if (err.status === 403) {
                        this.router.navigate(['/forbidden']);
                    } else if (err.status === 200) {
                        console.log("OK STATUS");
                        return throwError("OK");
                    } 

                    return throwError("Something is wrong");
                }
            )
        );
    }

    private addBackEndToken(request:HttpRequest<any>, backendToken:string) {
        return request.clone(
            {
                setHeaders: {
                    Authorization : `Bearer ${backendToken}`
                }
            }
        );
    } 
    private addOktaToken(request: HttpRequest<unknown>, oktaToken: string): HttpRequest<unknown> {
        let req = request;
        const allowedOrigins = ['http://localhost', 'https://hcl-capstone-ecommerce-group1-backend.azurewebsites.net', 'https://hcl-capstone-ecommerce-group1-frontend.azurewebsites.net'];
        if (!!allowedOrigins.find(origin => request.url.includes(origin))) {
          req = request.clone({ setHeaders: { 'Authorization': `Bearer ${oktaToken}` } });
        }
    
        return req;
      }

}