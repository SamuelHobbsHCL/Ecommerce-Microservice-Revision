import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { OktaAuthStateService, OKTA_AUTH } from '@okta/okta-angular';
import { authStateSpy } from '../global/auth.state';
import { oktaAuth } from '../global/okta.auth';

import { UserAuthService } from './user-auth.service';

describe('UserAuthService', () => {
  let service: UserAuthService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [ RouterTestingModule ],
      providers: [
        { provide: OktaAuthStateService, useValue: authStateSpy }, 
        { provide: OKTA_AUTH, useValue: { oktaAuth } } 
      ]
    });
    service = TestBed.inject(UserAuthService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
