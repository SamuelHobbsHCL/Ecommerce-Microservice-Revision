import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileSecurityComponent } from './user-profile-security.component';

describe('UserProfileSecurityComponent', () => {
  let component: UserProfileSecurityComponent;
  let fixture: ComponentFixture<UserProfileSecurityComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProfileSecurityComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfileSecurityComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
