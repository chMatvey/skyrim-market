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

  get(id: number): Observable<Order> {
    return this.http.get<Order>(`${apiUrl}/order/${id}`)
  }
}
