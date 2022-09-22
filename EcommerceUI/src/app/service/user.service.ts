import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserAuthService } from './user-auth.service';
import {map} from 'rxjs/operators';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UpdateImageDTO } from '../UpdateImageDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  PATH_OF_API = environment.apiUrl;

  requestHeader = new HttpHeaders({ 'No-Auth': 'True' });

  constructor(private httpClient: HttpClient, private userAuthService: UserAuthService) { }

  public login(loginData: any) {
    return this.httpClient.post(this.PATH_OF_API + '/auth/login', loginData, {
      headers: this.requestHeader,
    });
  }

  public roleMatch(allowedRoles: any) : boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();

    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    }
    return isMatch;
  }

  public getAllUsers() : Observable<any> {
    return this.httpClient.get<any>(this.PATH_OF_API + "/admin/users/")
    .pipe(map((res:any)=>{
      return res;
    }));
  }
   
  public getCurrentUser() : Observable<any> {
    return this.httpClient.get<any>(this.PATH_OF_API + "/user/getCurrentUser");
  }

  public getUserById(id: number) : Observable<any> {
    return this.httpClient.get<any>(this.PATH_OF_API + "/admin/user/" + id);
  }

  public updatePassword(passwordDTO) : Observable<any>{
    return this.httpClient.put<any>(this.PATH_OF_API + "/user/update-password", passwordDTO);
  }

  public deleteCurrentUser() : Observable<any>{
    return this.httpClient.delete<any>(this.PATH_OF_API + "/user/delete-user");
  }

  public updateUserImage(updateImageDTO : UpdateImageDTO) : Observable<any>{
    console.log(updateImageDTO);
    console.log("update user image");
    return this.httpClient.put<any>(this.PATH_OF_API + "/user/update-profile-image", updateImageDTO);
  }

 
}
