import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '@models/order';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '@services/order.service';
import { map, switchMap, tap } from 'rxjs/operators';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { OrderStatus } from '@models/order-status';
import { withLoading } from '@utils/stream-pipe-operators';

@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.scss']
})
export class ConfirmOrderComponent implements OnInit {

  order$: Observable<Order>

  loading: boolean

  form: FormGroup

  private order: Order

  constructor(private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.order$ = this.activateRoute.params
      .pipe(
        map(params => params['id']),
        switchMap(id => this.orderService.get(id)),
        tap(order => this.order = order)
      )

    this.form = new FormGroup({
      price: new FormControl(null, [Validators.required])
    })
  }

  close() {
    this.router.navigate(['/master/orders'])
  }

  decline() {
    this.orderService.update({...this.order, status: OrderStatus.DECLINED})
      .pipe(withLoading(this))
      .subscribe()
  }
}
