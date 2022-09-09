import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  constructor(private http : HttpClient) { }

  getProduct(){
    return this.http.get<any>("http://localhost:8081/api/products")
    .pipe(map((res:any)=>{
      return res;
    }))
  }


}
