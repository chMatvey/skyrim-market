import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order';
import { OrderService } from '@services/order.service';
import { Router } from '@angular/router';
import { withLoading } from '@utils/stream-pipe-operators';
import { AuthService } from '@services/auth.service';

@Component({
  selector: 'app-my-orders',
  templateUrl: './my-orders.component.html',
  styleUrls: ['./my-orders.component.scss']
})
export class MyOrdersComponent implements OnInit {

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
    this.orderService.getEmployeeOrders(this.userId)
      .pipe(withLoading(this))
      .subscribe(
        orders => this.orders = orders,
        error => console.log(error)
      )
  }

  openOrder(id: number) {
    this.router.navigate([`/employee/my-order/${id}`])
  }
}
