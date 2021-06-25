import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '@models/order';
import { apiUrl } from '@app/app.const';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { OrderStatus } from '@models/order-status';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order`, order)
  }

  update(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order/${order.id}`, order)
  }

  getClientOrder(id: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/all/client/${id}`)
  }

  get(id: number): Observable<Order> {
    return this.http.get<Order>(`${apiUrl}/order/${id}`)
  }

  getAvailableOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/all`)
      .pipe(
        map(orders => orders.filter(order => order.status === OrderStatus.PAYED))
      )
  }

  getEmployeeOrders(id: number): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/all/contractor/${id}`)
      .pipe(
        map(orders => orders.filter(order => order.status === OrderStatus.IN_PROGRESS))
      )
  }

  getMasterOrders() {
    return this.http.get<Order[]>(`${apiUrl}/order/all`)
      .pipe(
        map(orders => orders.filter(order => order.status === OrderStatus.CREATED))
      )
  }
}
