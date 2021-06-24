import { Component, OnInit } from '@angular/core';
import { map, switchMap, tap } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order';
import { AuthService } from '@services/auth.service';
import { withLoading } from '@utils/stream-pipe-operators';

@Component({
  selector: 'app-available-order',
  templateUrl: './available-order.component.html',
  styleUrls: ['./available-order.component.scss']
})
export class AvailableOrderComponent implements OnInit {

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
    this.router.navigate(['/employee/available-orders'])
  }

  assignToMe() {
    this.orderService.update({...this.order, contractor: this.userId})
      .pipe(
        withLoading(this),
        map(order => order.id)
      )
      .subscribe(id => this.router.navigate([`/employee/my-order/${id}`]))
  }
}
