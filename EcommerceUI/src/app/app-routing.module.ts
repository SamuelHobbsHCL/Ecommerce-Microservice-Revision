
import { Component } from '@angular/core';
import { InventoryComponent } from './admin/inventory/inventory.component';
import { CartComponent } from './cart/cart.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { LoginComponent } from './login/login.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductsComponent } from './products/products.component';
import { RegistrationComponent } from './registration/registration.component';
import { ApiService } from './service/api.service';
import { HomePageComponent } from './home-page/home-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { SelfUpdateComponent } from './self-update/self-update.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { AddProductComponent } from './add-product/add-product.component';
import { UpdateProductComponent } from './admin/update-product/update-product.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { OrdersComponent } from './orders/orders.component';
import { OrdersListComponent } from './admin/orders-list/orders-list.component';
import { UserProfileSecurityComponent } from './user-profile/user-profile-security/user-profile-security.component';
import { UserProfileOrderHistoryComponent } from './user-profile/user-profile-order-history/user-profile-order-history.component';
import { MyProfileComponent } from './user-profile/my-profile/my-profile.component';
import { OrderDetailsComponent } from './admin/order-details/order-details.component';


const routes: Routes = [
  {path: '', redirectTo: 'home-page', pathMatch: 'full'},
  {path: 'home-page', component: HomePageComponent},
  {path: 'products', component: ProductsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'login', component: LoginComponent},
  {path: 'loginSuccess', component: LoginSuccessComponent},
  {path: 'registration',component: RegistrationComponent},
  {path: 'forbidden',component: ForbiddenComponent},
  {path: 'admin',component: AdminComponent},
  {path: 'login/callback', component: OktaCallbackComponent},
  {path: 'product-details/:id', component: ProductDetailsComponent},
  {path: 'inventory', component: InventoryComponent},
  {path: 'inventory/add', component:AddProductComponent},
  {path: 'update/:userid', component: UserUpdateComponent},
  {path: 'about', component: AboutUsComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'updateproduct/:id', component: UpdateProductComponent},
  {path: 'self-update', component: SelfUpdateComponent},
  {path: 'check-out', component: CheckoutComponent},
  {path: 'user-profile', component: UserProfileComponent},
  {path: 'self-update/:userid', component: SelfUpdateComponent},
  {path: 'orders',component: OrdersComponent},
  {path: 'orders-list',component: OrdersListComponent},
  {path: 'my-profile', component: MyProfileComponent},
  {path: 'user-profile-security', component: UserProfileSecurityComponent},
  {path: 'user-profile-order-history', component: UserProfileOrderHistoryComponent},
  {path: 'orders-list/:id', component: OrderDetailsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
