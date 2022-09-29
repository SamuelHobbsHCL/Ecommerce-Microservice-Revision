import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { MyProfileComponent } from './my-profile.component';
import { oktaAndCloudinaryProvider, oktaProvider, testImports } from 'src/app/global/test.global';
import { CloudinaryService } from 'src/app/service/cloudinary.service';

describe('MyProfileComponent', () => {
  let component: MyProfileComponent;
  let fixture: ComponentFixture<MyProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: testImports,
      declarations: [ MyProfileComponent ],
      providers: oktaAndCloudinaryProvider
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MyProfileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
