import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../common/order';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';



@Injectable({
  providedIn: 'root'
})
export class OrderService {

  ECOMM_API_PATH = environment.ecommGatewayUrl;
  
  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  order: Order | undefined;
  
  constructor(private http : HttpClient) { }

  getOrderDetail(){
    return this.http.get<any>(this.ECOMM_API_PATH + "/user/get-order")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
}
