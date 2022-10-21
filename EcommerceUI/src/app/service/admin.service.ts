import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Product } from '../common/product';
import { environment } from 'src/environments/environment';
import { OrderDto } from '../common/orderDto';
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  PATH_OF_API = environment.apiUrl;
  ECOMM_API_PATH = environment.ecommGatewayUrl;
  PRODUCT_API_PATH = environment.productGatewayUrl;

  constructor(private http : HttpClient) { }

  public getAllUsers(){
    return this.http.get<any>(this.ECOMM_API_PATH + "/admin/users")
    .pipe(map((res:any)=>{
      return res;
    }));
  }
  public getAllProducts(){
    return this.http.get<any>(this.ECOMM_API_PATH + "/admin/products")
    .pipe(map((res:any)=>{
      return res;
    }));
  }

  public getAllOrders(){
    return this.http.get<any>(this.ECOMM_API_PATH + "/admin/orders")
      .pipe(map((res:any)=>{
        return res;
      }))
  }

  public getOrderById(id : any){
    return this.http.get<any>(this.ECOMM_API_PATH + "/admin/order/" + id)
    .pipe(map((res:any)=>{
      return res;
    }))
  }

  public deleteUser(id : any) {
    return this.http.delete(this.ECOMM_API_PATH + "/admin/delete-user/" + id);
  }

  public addProduct(product : Product) : Observable<any> {
    return this.http.post<any>(this.PRODUCT_API_PATH + "/admin/add-product/", product, {
    });
  }
  
  public deleteProduct(productId : any) {
    return this.http.delete(this.PRODUCT_API_PATH + "/admin/delete-product/" + productId);
  }

  public updateProduct(product : any) {
    return this.http.put(this.PRODUCT_API_PATH + "/admin/product", product);
  }

  public updateOrder(id : any, orderDTO : OrderDto){
    console.log(orderDTO.dtoStatus);
    console.log("Updating order status");
    return this.http.put(this.ECOMM_API_PATH + "/admin/order/update/" + id, orderDTO);
  }
}
