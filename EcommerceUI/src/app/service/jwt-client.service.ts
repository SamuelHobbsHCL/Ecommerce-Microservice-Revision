import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JwtClientService {

  constructor(private http:HttpClient) { }

  public generateToken(request: any) {
    return this.http.post("http://localhost:8080/auth/login", request, {responseType: 'text' as 'json'})
  }

}
