import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { ProductsComponent } from './products/products.component';
import { HeaderComponent } from './header/header.component';
import { CartComponent } from './cart/cart.component';
import { FilterPipe } from './shared/filter.pipe';
import { RouterModule } from '@angular/router';
import { AuthGuard } from './auth/auth.guard';
import { AuthInterceptor } from './auth/auth.interceptor';
import { CartService } from './service/cart.service';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { UserService } from './service/user.service';
import { AdminComponent } from './admin/admin.component';
import { CustomerListComponent } from './admin/customer-list/customer-list.component';
import { InventoryComponent } from './admin/inventory/inventory.component';
import { OktaAuthModule, OKTA_CONFIG } from '@okta/okta-angular';
import { OktaAuth } from '@okta/okta-auth-js';
import { HomePageComponent } from './home-page/home-page.component';
import { NewProductsComponent } from './home-page/new-products/new-products.component';
import { OurCollectionsComponent } from './home-page/our-collections/our-collections.component';
import { SplashPageComponent } from './home-page/splash-page/splash-page.component';
import { FooterComponent } from './footer/footer.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { SelfUpdateComponent } from './self-update/self-update.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';

const oktaAuth = new OktaAuth({
  issuer: 'https://dev-06861319.okta.com/oauth2/default',
  clientId: '0oa6b7ee0wwOnJzuz5d7',
  redirectUri: window.location.origin + '/login/callback'
});

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    LoginSuccessComponent,
    ProductsComponent,
    HeaderComponent,
    CartComponent,
    FilterPipe,
    ForbiddenComponent,
    AdminComponent,
    CustomerListComponent,
    InventoryComponent,
    HomePageComponent,
    NewProductsComponent,
    OurCollectionsComponent,
    SplashPageComponent,
    FooterComponent,
    ProductDetailsComponent,
    UserUpdateComponent,
    SelfUpdateComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    RouterModule,
    OktaAuthModule,
    BrowserAnimationsModule,
    MatPaginatorModule
  ],
  providers: [
    AuthGuard,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    CartService,
    UserService,
    { provide: OKTA_CONFIG, useValue: { oktaAuth } }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
