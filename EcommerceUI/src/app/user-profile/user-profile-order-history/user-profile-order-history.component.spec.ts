import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProfileOrderHistoryComponent } from './user-profile-order-history.component';

describe('UserProfileOrderHistoryComponent', () => {
  let component: UserProfileOrderHistoryComponent;
  let fixture: ComponentFixture<UserProfileOrderHistoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProfileOrderHistoryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UserProfileOrderHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
