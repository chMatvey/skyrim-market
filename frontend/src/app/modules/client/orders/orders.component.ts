import { Component, OnInit } from '@angular/core';
import { OrderService } from '@services/order.service';
import { Order } from '@models/order';
import { Router } from '@angular/router';
import { withLoading } from '@utils/stream-pipe-operators';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.scss']
})
export class OrdersComponent implements OnInit {

  orders: Order[]

  loading: boolean

  constructor(private orderService: OrderService,
              private router: Router,
              private authService: AuthService) {
  }

  get userId() {
    return this.authService.user.id
  }

  ngOnInit(): void {
    this.orderService.getClientOrder(this.userId)
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  openOrder(id: number) {
    this.router.navigate([`/client/order/${id}`])
  }
}
