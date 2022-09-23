import { InventoryComponent } from './admin/inventory/inventory.component';
import { CartComponent } from './cart/cart.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { LoginComponent } from './login/login.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { ProductsComponent } from './products/products.component';
import { HomePageComponent } from './home-page/home-page.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
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
import { CustomerListComponent } from './admin/customer-list/customer-list.component';


const routes: Routes = [
  {path: '', redirectTo: 'home-page', pathMatch: 'full'},
  {path: 'home-page', component: HomePageComponent},
  {path: 'products', component: ProductsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'auth/:method', component: LoginComponent},
  {path: 'loginSuccess', component: LoginSuccessComponent},
  // {path: 'auth/:method',component: LoginComponent},
  {path: 'forbidden',component: ForbiddenComponent},
  {path: 'admin', redirectTo: '/admin/user-management', pathMatch: 'full'},
  {path: 'admin', component: AdminComponent, children:[
    {path: 'inventory-management', component: InventoryComponent},
    {path: 'inventory-management/add-product', component:AddProductComponent},
    {path: 'inventory-management/update-product/:id', component: UpdateProductComponent},
    {path: 'user-management', component: CustomerListComponent},
    {path: 'user-management/update-user/:userid', component: UserUpdateComponent},
    {path: 'order-management',component: OrdersListComponent},
    {path: 'order-management/view-order/:id', component: OrderDetailsComponent}
  ]},
  {path: 'login/callback', component: OktaCallbackComponent},
  {path: 'product-details/:id', component: ProductDetailsComponent},
  {path: 'about', component: AboutUsComponent},
  {path: 'contact', component: ContactComponent},
  {path: 'check-out', component: CheckoutComponent},
  {path: 'user-profile', component: UserProfileComponent},
  {path: 'orders',component: OrdersComponent},
  {path: 'my-profile', component: MyProfileComponent},
  {path: 'user-profile-security', component: UserProfileSecurityComponent},
  {path: 'user-profile-order-history', component: UserProfileOrderHistoryComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
