import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '@models/order';
import { apiUrl } from '@app/app.const';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  public create(order: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order`, order)
  }

  public delete(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order`, order)
  }
}
