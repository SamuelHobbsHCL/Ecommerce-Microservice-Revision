import { ComponentFixture, TestBed } from '@angular/core/testing';
import { oktaProvider, testImports } from '../global/test.global';

import { CheckoutComponent } from './checkout.component';

// NOTE - for this test, stripe payment isn't possible as the script is only in index.html
// (thus not being called during jasmine tests)

describe('CheckoutComponent', () => {
  let component: CheckoutComponent;
  let fixture: ComponentFixture<CheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CheckoutComponent ],
      imports: testImports,
      providers: oktaProvider
    })
    .compileComponents();
  });

  // No tests due to Stripe being undefined w/out script
});
