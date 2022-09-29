import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { authState, authStateSpy } from 'src/app/global/auth.state';
import { oktaProvider, testImports } from 'src/app/global/test.global';

import { SplashPageComponent } from './splash-page.component';

describe('SplashPageComponent', () => {
  let component: SplashPageComponent;
  let fixture: ComponentFixture<SplashPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SplashPageComponent ],
      imports: testImports,
      providers: oktaProvider
    })
    .compileComponents();
  });

  beforeEach(() => {
    (Object.getOwnPropertyDescriptor(authStateSpy, 'authState$')?.get as jasmine.Spy).and.returnValue(of({authState}));
    fixture = TestBed.createComponent(SplashPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
