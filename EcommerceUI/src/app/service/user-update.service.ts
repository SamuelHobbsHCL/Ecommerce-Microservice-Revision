import { Injectable } from '@angular/core';
import { User } from '../user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UpdateService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  PATH_OF_API = environment.apiUrl;

  constructor(private http : HttpClient) { }

  public updateUser(id : any, userInfo:User) {
    console.log(id);
    return this.http.put(this.PATH_OF_API+"/admin/user/"+id,userInfo);
  }
}
