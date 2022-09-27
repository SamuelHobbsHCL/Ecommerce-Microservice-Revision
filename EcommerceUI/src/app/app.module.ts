import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
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
import { CheckoutComponent } from './checkout/checkout.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { AddProductComponent } from './add-product/add-product.component';
import { UpdateProductComponent } from './admin/update-product/update-product.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule } from '@angular/material/paginator';
import { OrdersComponent } from './orders/orders.component';
import { OrdersListComponent } from './admin/orders-list/orders-list.component';
import { UserProfileSecurityComponent } from './user-profile/user-profile-security/user-profile-security.component';
import { UserProfileOrderHistoryComponent } from './user-profile/user-profile-order-history/user-profile-order-history.component';
import { MyProfileComponent } from './user-profile/my-profile/my-profile.component';
import { CloudinaryModule } from '@cloudinary/ng';
import { OrderDetailsComponent } from './admin/order-details/order-details.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatListModule } from '@angular/material/list';
import { AddUserComponent } from './admin/add-user/add-user.component';


const oktaAuth = new OktaAuth({
  issuer: 'https://dev-06861319.okta.com/oauth2/default',
  clientId: '0oa6b7ee0wwOnJzuz5d7',
  redirectUri: window.location.origin + '/login/callback'
});

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
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
    AboutUsComponent,
    ContactComponent,
    AddProductComponent,
    UpdateProductComponent,
    CheckoutComponent,
    UserProfileComponent,
    OrdersComponent,
    OrdersListComponent,
    UserProfileComponent,
    UserProfileSecurityComponent,
    UserProfileOrderHistoryComponent,
    MyProfileComponent,
    OrderDetailsComponent,
    AddUserComponent
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
    MatPaginatorModule,
    CloudinaryModule,
    MatProgressSpinnerModule,
    MatSidenavModule,
    MatToolbarModule,
    MatMenuModule,
    MatIconModule,
    MatDividerModule,
    MatListModule
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
