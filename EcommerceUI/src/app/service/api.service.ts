import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';

import { Product }from '../common/product';
@Injectable({
  providedIn: 'root'
})
export class ApiService {

  requestHeader = new HttpHeaders(
    { "No-Auth" : "True"}
  );
  
  constructor(private http : HttpClient) { }

  getProduct(){
    return this.http.get<any>("http://localhost:8080/api/products")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
  product: Product | undefined;

  getProductById(id: string){
    this.http.get<Product>("http://localhost:8080/product/" + id).subscribe((data: Product) => this.product = {
    productId: (data as any).productId,
    productName: (data as any).productName,
    unitPrice: (data as any).unitPrice,
    productStock: (data as any).productStock,
    productImage: (data as any).productImage,
    productDescription: (data as any).productDescription
    }
    );
    console.log(this.product);
    return this.product;


}
}