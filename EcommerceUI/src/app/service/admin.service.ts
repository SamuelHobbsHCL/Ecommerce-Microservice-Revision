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

  constructor(private http : HttpClient) { }

  public getAllUsers(){
    return this.http.get<any>(this.PATH_OF_API + "/admin/users")
    .pipe(map((res:any)=>{
      return res;
    }));
  }
  public getAllProducts(){
    return this.http.get<any>(this.PATH_OF_API + "/admin/products")
    .pipe(map((res:any)=>{
      return res;
    }));
  }

  public getAllOrders(){
    return this.http.get<any>(this.PATH_OF_API + "/admin/orders")
      .pipe(map((res:any)=>{
        return res;
      }))
  }

  public getOrderById(id : any){
    return this.http.get<any>(this.PATH_OF_API + "/admin/order/" + id)
    .pipe(map((res:any)=>{
      return res;
    }))
  }

  public deleteUser(id : any) {
    return this.http.delete(this.PATH_OF_API + "/admin/delete-user/" + id);
  }

  public addProduct(product : Product) : Observable<any> {
    return this.http.post<any>(this.PATH_OF_API + "/admin/add-product/", product, {
    });
  }
  
  public deleteProduct(productId : any) {
    return this.http.delete(this.PATH_OF_API + "/admin/product/" + productId);
  }

  public updateProduct(product : any) {
    return this.http.put(this.PATH_OF_API + "/admin/product", product);
  }

  public updateOrder(id : any, orderDTO : OrderDto){
    console.log(orderDTO.dtoStatus);
    console.log("Updating order status");
    return this.http.put(this.PATH_OF_API + "/admin/order/update/" + id, orderDTO);
  }
}
