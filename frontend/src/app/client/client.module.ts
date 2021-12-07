import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { ClientComponent } from './client.component';
import { ClientRoutingModule } from './client-routing.module';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from './orders/orders.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { PayFormComponent } from './order/pay-form/pay-form.component';
import { ItemLocationComponent } from './order/item-location/item-location.component';
import { ForgeryOrderFormComponent } from './order/order-form/forgery-order-form/forgery-order-form.component';
import { PickpocketingOrderFormComponent } from './order/order-form/pickpocketing-order-form/pickpocketing-order-form.component';
import { SweepOrderFormComponent } from './order/order-form/sweep-order-form/sweep-order-form.component';
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { ClientState } from '@state/client/client.state';
import { CommentComponent } from './order/comment/comment.component'
import { MatButtonToggleModule } from '@angular/material/button-toggle'

@NgModule({
  declarations: [
    ClientComponent,
    OrderComponent,
    OrdersComponent,
    TopProductsComponent,
    PayFormComponent,
    ItemLocationComponent,
    ForgeryOrderFormComponent,
    PickpocketingOrderFormComponent,
    SweepOrderFormComponent,
    CommentComponent
  ],
  imports: [
    SharedModule,
    ClientRoutingModule,
    NgxsModule.forFeature([AppState, ClientState])
  ]
})
export class ClientModule { }
