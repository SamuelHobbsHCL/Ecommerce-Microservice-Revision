import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class AddProductService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private _http : HttpClient) { }

  public addProduct(product: Product) : Observable<any> {
    return this._http.post<any>("http://localhost:8080/inventory/add", product, {
      headers: this.requestHeader
    });
  }
}
