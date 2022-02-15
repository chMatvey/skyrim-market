import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs'
import { Order } from '@models/order/order'
import { ActivatedRoute } from '@angular/router'
import { Store } from '@ngxs/store'
import { OrderService } from '@services/order.service'
import { filter, map, switchMap, tap } from 'rxjs/operators'
import { withLoading } from '@utils/loading-util'
import { Navigate } from '@ngxs/router-plugin'

@Component({
  selector: 'app-completed-order',
  templateUrl: './completed-order.component.html',
  styleUrls: ['./completed-order.component.scss']
})
export class CompletedOrderComponent implements OnInit {
  order$: Observable<Order>
  private order: Order

  loading = true

  constructor(private activateRoute: ActivatedRoute,
              private store: Store,
              private orderService: OrderService) {
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        filter(id => !!id),
        switchMap(id => this.orderService.get(id).pipe(withLoading(this))),
        tap(order => this.order = order)
      )
  }

  close() {
    this.store.dispatch(new Navigate(['/student/my-orders']))
  }
}
