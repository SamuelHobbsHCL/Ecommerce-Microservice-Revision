import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  PATH_OF_API = environment.apiUrl;
  ECOMM_API_PATH = environment.ecommGatewayUrl;

  constructor(private _http : HttpClient) { }

  public registerUser(user: User) : Observable<any> {
    return this._http.post<any>(this.ECOMM_API_PATH + "/auth/signup", user, {
      headers: this.requestHeader
    });
  }
}
