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

  constructor(private _http : HttpClient) { }

  public registerUser(user: User) : Observable<any> {
    return this._http.post<any>(this.PATH_OF_API + "/auth/signup", user, {
      headers: this.requestHeader
    });
  }
}
