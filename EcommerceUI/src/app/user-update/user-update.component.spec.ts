import { ComponentFixture, TestBed } from '@angular/core/testing';
import { oktaProvider, testImports } from '../global/test.global';

import { UserUpdateComponent } from './user-update.component';

describe('UserUpdateComponent', () => {
  let component: UserUpdateComponent;
  let fixture: ComponentFixture<UserUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserUpdateComponent ],
      imports: testImports,
      providers: oktaProvider
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
