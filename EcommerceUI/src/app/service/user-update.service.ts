import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UpdateService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  PATH_OF_API = 'http://localhost:8080';

  constructor(private http : HttpClient) { }

  public updateUser(id : any, userInfo:User) {
    console.log(id);
    return this.http.put(this.PATH_OF_API+"/admin/user/"+id,userInfo);
  }
}
