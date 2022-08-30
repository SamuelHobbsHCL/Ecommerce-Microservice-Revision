import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OktaCallbackComponent } from '@okta/okta-angular';
import { AdminComponent } from './admin/admin.component';
import { CartComponent } from './cart/cart.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { LoginSuccessComponent } from './login-success/login-success.component';
import { LoginComponent } from './login/login.component';
import { ProductsComponent } from './products/products.component';
import { RegistrationComponent } from './registration/registration.component';

const routes: Routes = [
  {path: '', redirectTo: 'products', pathMatch: 'full'},
  {path: 'products', component: ProductsComponent},
  {path: 'cart', component: CartComponent},
  {path: 'login', component: LoginComponent},
  {path: 'loginSuccess', component: LoginSuccessComponent},
  {path: 'registration',component: RegistrationComponent},
  {path: 'forbidden',component: ForbiddenComponent},
  {path: 'admin',component: AdminComponent},
  {path: 'login/callback', component: OktaCallbackComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
