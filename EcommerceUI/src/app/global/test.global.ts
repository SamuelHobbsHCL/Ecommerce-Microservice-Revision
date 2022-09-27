import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { OktaAuthStateService, OKTA_AUTH } from "@okta/okta-angular";
import { authStateSpy } from "./auth.state";
import { oktaAuth } from "./okta.auth";

export const testImports = [ 
    HttpClientTestingModule, 
    RouterTestingModule,
    FormsModule
];
export const oktaProvider = [
    { provide: OktaAuthStateService, useValue: authStateSpy }, 
    { provide: OKTA_AUTH, useValue: { oktaAuth } } 
];