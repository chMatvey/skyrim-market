import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { OrderService } from '@services/order/order.service';
import { Router } from '@angular/router';
import { withLoading } from '@utils/stream-pipe-operators';

@Component({
  selector: 'app-available-orders',
  templateUrl: './available-orders.component.html',
  styleUrls: ['./available-orders.component.scss']
})
export class AvailableOrdersComponent implements OnInit {

  orders: Order[]

  loading: boolean

  constructor(private orderService: OrderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.orderService.getAvailableOrders()
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  openOrder(id: number) {
    this.router.navigate([`/employee/available-order/${id}`])
  }
}
