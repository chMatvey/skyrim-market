import { Component, OnInit } from '@angular/core';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order';
import { OrderStatus } from '@models/order-status';
import { orderStatusToString } from '@services/order-status';
import { Router } from '@angular/router';
import { withLoading } from '@utils/stream-pipe-operators';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders: Order[]

  loading = true

  constructor(private orderService: OrderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.orderService.getAll()
      .pipe(withLoading(this))
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
