import { Component, OnInit } from '@angular/core';
import { Order } from '@models/order/order';
import { ActivatedRoute } from '@angular/router';
import { OrderService } from '@services/order.service';
import { map, switchMap, takeUntil } from 'rxjs/operators';
import { FormGroup } from '@angular/forms';
import { MatDialog } from '@angular/material/dialog';
import { Store } from '@ngxs/store'
import { Navigate } from '@ngxs/router-plugin'
import { createMasterOrderForm } from '@utils/order-util'
import { MasterOrderService } from '@services/order/master-order.service'
import { User } from '@models/user'
import { ContractorService } from '@services/contractor.service'
import { FormComponent } from '@app/shared/form/form.component'

@Component({
  selector: 'app-confirm-order',
  templateUrl: './confirm-order.component.html',
  styleUrls: ['./confirm-order.component.scss']
})
export class ConfirmOrderComponent extends FormComponent implements OnInit {
  orderForm: FormGroup
  order: Order

  contractors: User[] = []

  constructor(private store: Store,
              private activateRoute: ActivatedRoute,
              private orderService: OrderService,
              private masterOrderService: MasterOrderService,
              private contractorService: ContractorService,
              dialogService: MatDialog) {
    super(dialogService)
  }

  get commentInvalid(): boolean {
    return this.orderForm.get('comment').invalid
  }

  get approveInvalid(): boolean {
    return this.orderForm.get('price').invalid
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
        this.orderForm = createMasterOrderForm(order)
      })

    this.contractorService.all().subscribe(contractors => this.contractors = contractors)
  }

  close(): void {
    this.store.dispatch(new Navigate(['/master/orders']))
  }

  decline(): void {
    const request$ = this.masterOrderService.decline(this.order.id, this.orderForm.value)
    this.sendForm(request$, 'Заказ успешно отменен!')
      .subscribe(() => this.store.dispatch(new Navigate(['/master/orders'])))
  }

  comment(): void {
    const request$ = this.masterOrderService.comment(this.order.id, this.orderForm.value)
    this.sendForm(request$, 'Заказ успешно прокомментирован!')
      .subscribe(({id}) => this.store.dispatch(new Navigate([`/master/orders`])))
  }

  approve(): void {
    const request$ = this.masterOrderService.approve(this.order.id, this.orderForm.value)
    this.sendForm(request$, 'Заказ успешно согласован!')
      .subscribe(() => this.store.dispatch(new Navigate([`/master/orders`])))
  }
}
