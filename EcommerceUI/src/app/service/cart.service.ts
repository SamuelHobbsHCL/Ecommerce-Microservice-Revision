import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { OrderInfo } from '../common/orderInfo';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  PATH_OF_API = environment.apiUrl;

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  constructor(private http : HttpClient) { }

  public addtoCart(product : any, quantity : any) : Observable<any> {
    return this.http.post<any>(this.PATH_OF_API + "/user/add-to-cart/" + product.productId + "/" + quantity, null);
  }

  public getOrderByUserId() : Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + "/user/get-order-in-progress");
  }

  public getProductByProductId(productId : any) : Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + "/product/" + productId);
  }

  public getOrderItemsByOrderId(orderId : any) : Observable<any> {
    return this.http.get<any>(this.PATH_OF_API + "/user/get-order-items/" + orderId);
  }

  // public checkOut(shippingAddress: Address, billingAddress: Address) {
  //  return this.http.post<any>(this.PATH_OF_API + "/user/check-out", [{ shippingAddress },{ billingAddress }]);
  // }
  public checkOut(orderInfo: OrderInfo) {
    return this.http.post<any>(this.PATH_OF_API + "/user/check-out", orderInfo);
  }

  public deleteAllOrderItemsByOrder() {
    return this.http.delete(this.PATH_OF_API + "/user/delete-all-order-items");
  }

  public deleteOrderItemById(id : any) {
    return this.http.delete(this.PATH_OF_API + "/user/delete-order-item/" + id);
  }

  public getAllOrderForCurrentUser(){
    return this.http.get(this.PATH_OF_API + "/user/get-order");
  }

}
