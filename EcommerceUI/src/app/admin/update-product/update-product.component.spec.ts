import { ComponentFixture, TestBed } from '@angular/core/testing';
import { testImports } from 'src/app/global/test.global';
import { CloudinaryService } from 'src/app/service/cloudinary.service';

import { UpdateProductComponent } from './update-product.component';

describe('UpdateProductComponent', () => {
  let component: UpdateProductComponent;
  let fixture: ComponentFixture<UpdateProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateProductComponent ],
      providers: [ CloudinaryService ],
      imports: testImports
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UpdateProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
