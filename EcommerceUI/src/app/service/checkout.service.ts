import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PaymentInfo } from '../common/payment-info';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  PATH_OF_API = environment.apiUrl;

  private paymentIntentUrl = this.PATH_OF_API + "/api/payment-intent";

  constructor(private httpClient: HttpClient) { }

  createPaymentIntent(paymentInfo: PaymentInfo): Observable<any>{
    return this.httpClient.post<PaymentInfo> (this.paymentIntentUrl, paymentInfo);
  }
}
