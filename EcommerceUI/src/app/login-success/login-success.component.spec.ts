import { ComponentFixture, TestBed } from '@angular/core/testing';
import { oktaProvider, testImports } from '../global/test.global';

import { LoginSuccessComponent } from './login-success.component';

describe('LoginSuccessComponent', () => {
  let component: LoginSuccessComponent;
  let fixture: ComponentFixture<LoginSuccessComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ LoginSuccessComponent ],
      imports: testImports,
      providers: oktaProvider
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginSuccessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
