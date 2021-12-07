import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'
import { apiUrl } from '@app/app.const'

@Injectable({
  providedIn: 'root'
})
export class EmployeeOrderService {

  constructor(private httpClient: HttpClient) {}

  all(id: number): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${apiUrl}/order/contractor/${id}`)
  }

  payed(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${apiUrl}/order/contractor/payed`)
  }

  completed(): Observable<Order[]> {
    return this.httpClient.get<Order[]>(`${apiUrl}/order/contractor/completed`)
  }

  assignToMe(orderId: number): Observable<Order> {
    return this.httpClient.get<Order>(`${apiUrl}/order/contractor/assign-to-me/${orderId}`)
  }

  decline(orderId: number, order: Order): Observable<Order> {
    return this.httpClient.patch<Order>(`${apiUrl}/order/contractor/decline/${orderId}`, order)
  }

  complete(orderId: number, order: Order): Observable<Order> {
    return this.httpClient.patch<Order>(`${apiUrl}/order/contractor/complete/${orderId}`, order)
  }
}
