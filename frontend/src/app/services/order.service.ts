import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '@models/order/order';
import { apiUrl } from '@app/app.const';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order`, order)
  }

  update(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order`, order)
  }

  get(id: number): Observable<Order> {
    return this.http.get<Order>(`${apiUrl}/order/${id}`)
  }
}
