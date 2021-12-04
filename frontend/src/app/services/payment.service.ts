import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Payment } from '@models/payment'
import { apiUrl } from '@app/app.const'

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private httpClient: HttpClient) { }

  all(): Observable<Payment[]> {
    return this.httpClient.get<Payment[]>(`${apiUrl}/payment`)
  }
}
