import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  PATH_OF_API = environment.apiUrl;
  constructor(private httpClient: HttpClient) { }

  public getUserAddress() : Observable<any> {
    return this.httpClient.get<any>(this.PATH_OF_API + "/user/getUserAddress");
  }
}
