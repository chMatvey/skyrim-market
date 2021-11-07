import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '@services/order.service';
import { map, switchMap, takeUntil, tap } from 'rxjs/operators';
import { FormGroup } from '@angular/forms';
import { withLoading } from '@utils/loading-util';
import { MatDialog } from '@angular/material/dialog';
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'
import { showNotification } from '@utils/notification-util'
import { BaseComponent } from '@app/shared/base/base.component'
import { createMasterOrderForm } from '@utils/order-util'
import { MasterOrderService } from '@services/order/master-order.service'
import { Entity } from '@models/entity'
import { User } from '@models/user'
import { ContractorService } from '@services/contractor.service'

@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.scss']
})
export class ConfirmOrderComponent extends BaseComponent implements OnInit {
  loading: boolean

  form: FormGroup
  order: Order

  contractors: User[] = []

  constructor(private store: Store,
              private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private masterOrderService: MasterOrderService,
              private contractorService: ContractorService,
              private dialogService: MatDialog) {
    super()
  }

  compareEntity(a: Entity, b: Entity): boolean {
    return a?.id === b?.id
  }

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(
        takeUntil(this.ngUnsubscribe),
        map(params => params['id']),
        switchMap(id => this.orderService.get(id))
      )
      .subscribe(order => {
        this.order = order
        this.form = createMasterOrderForm(order)
      })

    this.contractorService.all().subscribe(contractors => this.contractors = contractors)
  }

  close(): void {
    this.store.dispatch(new Navigate(['/master/orders']))
  }

  decline(): void {
    this.masterOrderService.decline(this.order.id)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Order successfully declined!'))
      )
      .subscribe(() => this.store.dispatch(new Navigate(['/master/orders'])))
  }

  apply(): void {
    this.masterOrderService.approve(this.order.id, this.form.value)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Order successfully approved!'))
      )
      .subscribe(({id}) => this.store.dispatch(new Navigate([`/master/order/${id}`])))
  }
}
