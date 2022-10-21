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
  ECOMM_API_PATH = environment.ecommGatewayUrl;
  PRODUCT_API_PATH = environment.productGatewayUrl;
  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  constructor(private http : HttpClient) { }

  public addtoCart(product : any, quantity : any) : Observable<any> {
    return this.http.post<any>(this.ECOMM_API_PATH + "/user/add-to-cart/" + product.productId + "/" + quantity, null);
  }

  public getOrderByUserId() : Observable<any> {
    return this.http.get<any>(this.ECOMM_API_PATH + "/user/get-order-in-progress");
  }

  public getProductByProductId(productId : any) : Observable<any> {
    return this.http.get<any>(this.PRODUCT_API_PATH + "/product/" + productId);
  }

  public getOrderItemsByOrderId(orderId : any) : Observable<any> {
    return this.http.get<any>(this.ECOMM_API_PATH + "/user/get-order-items/" + orderId);
  }

  public checkOut(orderInfo: OrderInfo) {
    return this.http.post<any>(this.ECOMM_API_PATH + "/user/check-out", orderInfo);
  }

  public deleteAllOrderItemsByOrder() {
    return this.http.delete(this.ECOMM_API_PATH + "/user/delete-all-order-items");
  }

  public deleteOrderItemById(id : any) {
    return this.http.delete(this.ECOMM_API_PATH + "/user/delete-order-item/" + id);
  }

  public getAllOrderForCurrentUser(){
    return this.http.get(this.ECOMM_API_PATH + "/user/get-order");
  }

}
