import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { data } from 'jquery';
import { Observable } from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import { Product }from '../common/product';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );

  product: Product | undefined;
  
  constructor(private http : HttpClient) { }

  getProduct(){
    return this.http.get<any>("http://localhost:8080/api/products")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
  
getProductById(id: string): Observable<Product>{
  return this.http.get<Product>("http://localhost:8080/api/product/" + id).pipe(map((data: Product) => this.product = {
    productId: (data as any).productId,
    productName: (data as any).productName,
    unitPrice: (data as any).unitPrice,
    productStock: (data as any).productStock,
    productImage: (data as any).productImage,
    productDescription: (data as any).productDescription
    }),
  catchError(error => this.throwError(error))
)

}

throwError(error: any) {
  console.error(error);
  return Observable.throw(error.json().error || 'Server error');
}


}
