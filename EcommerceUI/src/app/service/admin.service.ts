import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {map} from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class AdminService {

  PATH_OF_API = 'http://localhost:8080';

  constructor(private http : HttpClient) { }

  public getAllUsers(){
    return this.http.get<any>(this.PATH_OF_API + "/admin/users")
    .pipe(map((res:any)=>{
      return res;
    }));
  }
  public getAllProducts(){
    return this.http.get<any>(this.PATH_OF_API + "/admin/products")
    .pipe(map((res:any)=>{
      return res;
    }));
  }

  public deleteUser(id : any) {
    return this.http.delete(this.PATH_OF_API + "/admin/delete-user/" + id);
  }
  public deleteProduct(productId : any) {
    return this.http.delete(this.PATH_OF_API + "/admin/product/" + productId);
  }
}
