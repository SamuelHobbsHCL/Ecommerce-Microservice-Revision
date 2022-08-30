import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  PATH_OF_API = 'http://localhost:8080';

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

  public checkOut() {
    return this.http.post<any>(this.PATH_OF_API + "/user/check-out", null);
  }

  public deleteAllOrderItemsByOrder() {
    return this.http.delete(this.PATH_OF_API + "/user/delete-all-order-items");
  }

  public deleteOrderItemById(id : any) {
    return this.http.delete(this.PATH_OF_API + "/user/delete-order-item/" + id);
  }

}