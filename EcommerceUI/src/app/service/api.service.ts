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

  PATH_OF_API = 'http://localhost:8080';

  product: Product | undefined;
  products: Product[] | undefined;
  
  constructor(private http : HttpClient) { }

  public getProduct(){
    return this.http.get<any>(this.PATH_OF_API + "/api/products")
    .pipe(map((res:any)=>{
      return res;
    }))
  }
  
  public getProductById(id: string): Observable<Product>{
    return this.http.get<Product>(this.PATH_OF_API + "/api/product/" + id).pipe(map((data: Product) => this.product = {
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

  getSearchResult(searchStr: string, index: string, count: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.PATH_OF_API + "/api/product/search", { params: {searchStr, index, count}});
    /*
    return this.http.get(this.PATH_OF_API + "/api/product/search")
      .pipe(
        map((data: Product[]) => 
          (data as any).map((product: Product) => ({
            productId: product.productId,
            productName: product.productName,
            unitPrice: product.unitPrice,
            productStock: product.productStock,
            productImage: product.productImage,
            productDescription: product.productDescription
          }))
      )
    )
    */
  }

  throwError(error: any) {
    console.error(error);
    return Observable.throw(error.json().error || 'Server error');
  }

}