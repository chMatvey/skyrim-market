import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { withLoading } from '@utils/loading-util';
import { MasterOrderService } from '@services/order/master-order.service'
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'

@Component({
  selector: 'app-orders-for-master',
  templateUrl: './orders-for-master.component.html',
  styleUrls: ['./orders-for-master.component.scss']
})
export class OrdersForMasterComponent implements OnInit {

  orders: Order[]

  loading: boolean

  constructor(private orderService: MasterOrderService,
              private store: Store) {
  }

  ngOnInit(): void {
    this.orderService.created()
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  openOrder(id: number) {
    this.store.dispatch(new Navigate([`/master/order/${id}`]))
  }
}
