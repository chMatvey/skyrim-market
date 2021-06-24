import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Order } from '@models/order';
import { apiUrl } from '@app/app.const';
import { Observable } from 'rxjs';
import { AuthService } from '@services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient,
              private authService: AuthService) { }

  get clientId() {
    return this.authService.user.id
  }

  create(order: Order): Observable<Order> {
    return this.http.post<Order>(`${apiUrl}/order`, {...order, client: this.clientId})
  }

  update(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order`, {...order, client: this.clientId})
  }

  delete(order: Order): Observable<Order> {
    return this.http.put<Order>(`${apiUrl}/order`, {...order, client: this.clientId})
  }

  getAll(): Observable<Order[]> {
    return this.http.get<Order[]>(`${apiUrl}/order/all/client/${this.clientId}`)
  }

  get(id: number) {
    return this.http.get<Order>(`${apiUrl}/order/${id}`)
  }
}
