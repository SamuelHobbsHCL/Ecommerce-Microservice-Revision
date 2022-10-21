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
  ECOMM_API_PATH = environment.ecommGatewayUrl;
  
  constructor(private http : HttpClient) { }

  public updateSelf(id : any, userInfo:User) {
    return this.http.put(this.ECOMM_API_PATH + "/user/update/" + id, userInfo);
  }
}
