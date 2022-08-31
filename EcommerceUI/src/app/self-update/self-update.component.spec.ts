import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfUpdateComponent } from './self-update.component';

describe('SelfUpdateComponent', () => {
  let component: SelfUpdateComponent;
  let fixture: ComponentFixture<SelfUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelfUpdateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
