import { Component, OnInit } from '@angular/core';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { orderStatusToString } from '@services/order-status';
import { Router } from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders: Order[]

  constructor(private orderService: OrderService,
              private router: Router) {
  }

  get loading(): boolean {
    return !this.orders
  }

  ngOnInit(): void {
    this.orderService.getAll()
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  orderStatus(status: OrderStatus) {
    return orderStatusToString(status)
  }

  openOrder(id: number) {
    this.router.navigate([`/client/order/${id}`])
  }
}
