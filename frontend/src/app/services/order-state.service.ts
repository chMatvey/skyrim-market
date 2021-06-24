import { Injectable } from '@angular/core';
import { Order } from '@models/order';

@Injectable({
  providedIn: 'root'
})
export class OrderStateService {

  currentOrder: Order

  constructor() { }

  get order(): Order {
    return this.currentOrder
  }

  set order(value: Order) {
    this.currentOrder = value
  }
}
