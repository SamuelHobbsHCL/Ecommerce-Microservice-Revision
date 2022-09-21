import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../common/order';
import { map } from 'rxjs/operators';



@Injectable({
  providedIn: 'root'
})
export class OrderService {

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  order: Order | undefined;
  
  constructor(private http : HttpClient) { }

  getOrderDetail(){
    return this.http.get<any>("http://localhost:8080/user/get-order")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
}
