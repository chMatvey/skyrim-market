import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { OrderService } from '@services/order/order.service';
import { Router } from '@angular/router';
import { withLoading } from '@utils/stream-pipe-operators';

@Component({
  selector: 'app-orders-for-master',
  templateUrl: './orders-for-master.component.html',
  styleUrls: ['./orders-for-master.component.scss']
})
export class OrdersForMasterComponent implements OnInit {

  orders: Order[]

  loading: boolean

  constructor(private orderService: OrderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.orderService.getCreatedOrders()
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  openOrder(id: number) {
    this.router.navigate([`/master/confirm-order/${id}`])
  }
}
