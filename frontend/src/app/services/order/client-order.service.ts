import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { apiUrl } from '@app/app.const'
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'
import { Payment } from '@models/payment'

@Injectable({
  providedIn: 'root'
})
export class ClientOrderService {

  constructor(private http: HttpClient) {}

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order/client`, order)
  }

  update(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order/client`, order)
  }

  all(id: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/client/${id}`)
  }

  decline(id: number): Observable<Order> {
    return this.http.get(`${apiUrl}/order/client/decline/${id}`)
  }

  pay(id: number, payment: Payment): Observable<Order> {
    return this.http.patch(`${apiUrl}/order/client/pay/${id}`, payment)
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${apiUrl}/order/client/${id}`)
  }
}
