import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  PATH_OF_API = environment.apiUrl;

  constructor(private http:HttpClient) { }

  public generateToken(request: any) {
    return this.http.post(this.PATH_OF_API + "/auth/login", request, {responseType: 'text' as 'json'})
  }

}
