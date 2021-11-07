import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'
import { apiUrl } from '@app/app.const'

@Injectable({
  providedIn: 'root'
})
export class MasterOrderService {

  constructor(private http: HttpClient) {}

  created(): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/master/created`)
  }

  payed(): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/master/payed`)
  }

  decline(id: number): Observable<Order> {
    return this.http.get<Order>(`${apiUrl}/order/master/decline/${id}`)
  }

  approve(id: number, formValue: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order/master/approve/${id}`, formValue)
  }
}
