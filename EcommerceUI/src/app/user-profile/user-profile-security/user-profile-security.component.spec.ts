import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { authState, authStateSpy } from 'src/app/global/auth.state';
import { oktaProvider, testImports } from 'src/app/global/test.global';

import { UserProfileSecurityComponent } from './user-profile-security.component';

describe('UserProfileSecurityComponent', () => {
  let component: UserProfileSecurityComponent;
  let fixture: ComponentFixture<UserProfileSecurityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProfileSecurityComponent ],
      imports: testImports,
      providers: oktaProvider
    })
    .compileComponents();
  });

  beforeEach(() => {
    (Object.getOwnPropertyDescriptor(authStateSpy, 'authState$')?.get as jasmine.Spy).and.returnValue(of({authState}));
    fixture = TestBed.createComponent(UserProfileSecurityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
