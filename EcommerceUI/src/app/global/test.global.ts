import { HttpClientTestingModule } from "@angular/common/http/testing";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { RouterTestingModule } from "@angular/router/testing";
import { CloudinaryModule } from "@cloudinary/ng";
import { OktaAuthStateService, OKTA_AUTH } from "@okta/okta-angular";
import { CloudinaryService } from "../service/cloudinary.service";
import { authStateSpy } from "./auth.state";
import { oktaAuth } from "./okta.auth";

export const testImports = [ 
    HttpClientTestingModule, 
    RouterTestingModule,
    FormsModule,
    ReactiveFormsModule,
    CloudinaryModule
];

export const oktaProvider = [
    { provide: OktaAuthStateService, useValue: authStateSpy }, 
    { provide: OKTA_AUTH, useValue: { oktaAuth } } 
];

export const oktaAndCloudinaryProvider = [
    { provide: OktaAuthStateService, useValue: authStateSpy }, 
    { provide: OKTA_AUTH, useValue: { oktaAuth } },
    CloudinaryService
]