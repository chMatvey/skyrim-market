import { Injectable } from '@angular/core';
import { Order } from '@models/order';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OrderStateService {

  currentOrder$: BehaviorSubject<Order> = new BehaviorSubject<Order>(null)

  constructor() { }

  get order(): Order {
    return this.currentOrder$.value
  }

  set order(value: Order) {
    this.currentOrder$.next(value)
  }

  get order$(): Observable<Order> {
    return this.currentOrder$.asObservable()
  }
}
