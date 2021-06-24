import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ClientComponent } from './client.component';
import { ClientRoutingModule } from './client-routing.module';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from './orders/orders.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { OrderFormComponent } from './order/order-form/order-form.component';
import { PayFormComponent } from './order/pay-form/pay-form.component';
import { ItemLocationComponent } from './order/item-location/item-location.component';

@NgModule({
  declarations: [
    ClientComponent,
    OrderComponent,
    OrdersComponent,
    TopProductsComponent,
    OrderFormComponent,
    PayFormComponent,
    ItemLocationComponent
  ],
  imports: [
    SharedModule,
    ClientRoutingModule
  ]
})
export class ClientModule { }
