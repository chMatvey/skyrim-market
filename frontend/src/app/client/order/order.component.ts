import { AfterViewInit, Component, OnDestroy, OnInit } from '@angular/core';
import { createClientOrderForm } from '@utils/order-util';
import { Dropdown } from '@models/template/dropdown';
import { BaseComponent } from '@app/shared/base/base.component';
import { ActivatedRoute } from '@angular/router';
import { OrderTypeString } from '@models/order-type-string';
import { Order } from '@models/order/order';
import { OrderStatusEnum } from '@models/order-status-enum';
import { filter, map, switchMap, takeUntil, tap, withLatestFrom } from 'rxjs/operators';
import { Observable } from 'rxjs';
import { Store } from '@ngxs/store'
import { ClientState } from '@state/client/client.state'
import { Client } from '@state/client/client.actions'
import { showError, showNotification } from '@utils/notification-util'
import { MatDialog } from '@angular/material/dialog'
import { toMessage } from '@utils/http-util'
import { withLoading } from '@utils/loading-util'
import { Navigate } from '@ngxs/router-plugin'
import { FormGroup } from '@angular/forms'
import { TitleService } from '@services/title.service'
import { ItemService } from '@services/item.service'
import { Title } from '@models/title'
import { Item } from '@models/Item'
import { OrderService } from '@services/order.service'
import { getOrderTypes } from '@utils/order-type-util'
import { ClientOrderService } from '@services/order/client-order.service'
import Reset = Client.Reset
import SetOrderType = Client.SetOrderType
import DeclineOrder = Client.DeclineOrder
import UpdateOrder = Client.UpdateOrder
import CreateOrder = Client.CreateOrder
import SetOrder = Client.SetOrder
import APPROVED = OrderStatusEnum.APPROVED
import COMPLETED = OrderStatusEnum.COMPLETED
import NEED_CHANGES = OrderStatusEnum.NEED_CHANGES
import DECLINED = OrderStatusEnum.DECLINED
import { orderStatusToString } from "@utils/order-status-util";

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent extends BaseComponent implements OnInit, AfterViewInit, OnDestroy {
  order$: Observable<Order>
  orderForm: FormGroup
  private order: Order

  orderTypes: Dropdown<OrderTypeString>[] = getOrderTypes()
  orderType: OrderTypeString = null

  loading: boolean

  titles: Title[] = []
  items: Item[] = []

  constructor(private activateRoute: ActivatedRoute,
              private store: Store,
              private dialogService: MatDialog,
              private orderService: OrderService,
              private titleService: TitleService,
              private itemService: ItemService,
              private clientOrderService: ClientOrderService) {
    super()
  }

  get orderStatus() {
    return orderStatusToString(this.order?.status?.name)
  }

  get orderStatusOrig() {
    return this.order?.status?.name
  }

  get formDisabled(): boolean {
    return this.orderForm?.disabled
  }

  get saveDisabled(): boolean {
    if (!this.orderForm) {
      return true
    }
    return this.orderForm.invalid
  }

  get showComment(): boolean {
    const status: string | undefined = this.order?.status?.name
    return this.order?.comment && (status === NEED_CHANGES || status === DECLINED)
  }

  get showPayForm(): boolean {
    return this.order?.status?.name === APPROVED
  }

  get showItemLocation(): boolean {
    return this.order?.status?.name === COMPLETED
  }

  ngOnInit(): void {
    this.activateRoute.params
      .pipe(
        takeUntil(this.ngUnsubscribe),
        map(params => params['id']),
        filter(id => !!id),
        switchMap(id => this.orderService.get(id).pipe(withLoading(this))),
        tap(order => this.store.dispatch(new SetOrder(order))),
      )
      .subscribe(
        order => this.orderForm = createClientOrderForm(order),
        error => showError(this.dialogService, toMessage(error))
      )

    this.order$ = this.store.select(ClientState.order).pipe(
      takeUntil(this.ngUnsubscribe),
      filter(order => !!order?.type),
      tap(order => this.order = order)
    )

    this.titleService.all().subscribe(titles => this.titles = titles)
    this.itemService.all().subscribe(items => this.items = items)
  }

  ngAfterViewInit() {
    setTimeout(() => {
      this.store.select(ClientState.orderType)
        .pipe(takeUntil(this.ngUnsubscribe))
        .subscribe(type => this.orderType = type)
    })
  }

  ngOnDestroy() {
    this.store.dispatch(new Reset())
  }

  onOrderTypeChange(value: OrderTypeString) {
    this.orderType = value
    this.store.dispatch(new SetOrderType(value))
      .pipe(withLatestFrom(this.store.select(ClientState.order)))
      .subscribe(([,order]) => this.orderForm = createClientOrderForm(order))
  }

  onCreateOrder() {
    this.store.dispatch(new CreateOrder({...this.order, ...this.orderForm.value}))
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Заказ успешно создан!')),
        withLatestFrom(this.store.select(ClientState.order))
      )
      .subscribe(
        ([,order]) => this.store.dispatch([
          new Navigate([`/client/order/${order.id}`])
        ]),
        error => showError(this.dialogService, toMessage(error))
      )
  }

  onDeclineOrder() {
    this.store.dispatch(new DeclineOrder(this.order.id))
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService,'Заказ успешно отменен!'))
      )
      .subscribe(
        () => this.store.dispatch(new Navigate(['/client/orders'])),
        error => showError(this.dialogService, toMessage(error))
      )
  }

  onUpdateOrder() {
    this.store.dispatch(new UpdateOrder({...this.order, ...this.orderForm.value}))
      .pipe(withLoading(this))
      .subscribe(
        () => showNotification(this.dialogService, 'Заказ успешно обновлен!'),
        error => showError(this.dialogService, toMessage(error))
      )
  }

  onCancel() {
    this.store.dispatch(new Reset())
    this.store.dispatch(new Navigate(['/client/orders']))
  }

  onDelete() {
    this.clientOrderService.delete(this.order.id).pipe(withLoading(this))
      .subscribe(
        () => {
          showNotification(this.dialogService, 'Заказ успешно удален!')
          this.store.dispatch(new Navigate(['/client/orders']))
        },
        error => showError(this.dialogService, toMessage(error))
      )
  }
}
