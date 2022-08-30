import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../user';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private _http : HttpClient) { }

  public registerUser(user: User) : Observable<any> {
    return this._http.post<any>("http://localhost:8080/api/user-registration", user, {
      headers: this.requestHeader,
    });
  }
}
