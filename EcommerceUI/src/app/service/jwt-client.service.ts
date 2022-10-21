import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  PATH_OF_API = environment.apiUrl;
  ECOMM_API_PATH = environment.ecommGatewayUrl;

  constructor(private http:HttpClient) { }

  public generateToken(request: any) {
    return this.http.post(this.ECOMM_API_PATH + "/auth/login", request, {responseType: 'text' as 'json'})
  }

}
