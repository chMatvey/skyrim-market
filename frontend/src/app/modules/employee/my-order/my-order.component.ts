import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '@models/order';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '@services/order.service';
import { AuthService } from '@services/auth.service';
import { map, switchMap, tap } from 'rxjs/operators';
import { OrderStatus } from '@models/order-status';

@Component({
  selector: 'app-my-order',
  templateUrl: './my-order.component.html',
  styleUrls: ['./my-order.component.scss']
})
export class MyOrderComponent implements OnInit {

  order$: Observable<Order>

  loading: boolean

  private order: Order

  constructor(private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private router: Router,
              private authService: AuthService) {
  }

  get userId() {
    return this.authService.user.id
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        switchMap(id => this.orderService.get(id)),
        tap(order => this.order = order)
      )
  }

  close() {
    this.router.navigate(['/employee/my-orders'])
  }

  decline() {
    this.orderService.update({...this.order, status: OrderStatus.DECLINED})
  }

  complete() {
    this.orderService.update({...this.order, status: OrderStatus.COMPLETED})
  }
}
