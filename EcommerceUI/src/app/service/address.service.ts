import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AddressDTO } from '../addressDTO';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  PATH_OF_API = environment.apiUrl;
  ECOMM_API_PATH = environment.ecommGatewayUrl;
  constructor(private httpClient: HttpClient) { }

  public getUserAddress() : Observable<any> {
    return this.httpClient.get<any>(this.ECOMM_API_PATH + "/user/getUserAddress");
  }

  public getAddressById(userId: number) : Observable<any> {
    return this.httpClient.get<any>(this.ECOMM_API_PATH + "/admin/address/" + userId);
  }

  public updateUserAddress(addressDTO : AddressDTO) : Observable<any> {
    return this.httpClient.put<any>(this.ECOMM_API_PATH + "/user/update-address", addressDTO);
  }

  public updateAddressById(userId: number, addressDTO : AddressDTO) : Observable<any> {
    return this.httpClient.put<any>(this.ECOMM_API_PATH + "/admin/update-address/" + userId, addressDTO);
  }
}
