import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { ClientComponent } from './client.component';
import { ClientRoutingModule } from './client-routing.module';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from './orders/orders.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { PickpocketingFormComponent } from './order/pickpocketing-form/pickpocketing-form.component';
import { OrderFormFooterComponent } from './order/order-form-footer/order-form-footer.component';

@NgModule({
  declarations: [
    ClientComponent,
    OrderComponent,
    OrdersComponent,
    TopProductsComponent,
    PickpocketingFormComponent,
    OrderFormFooterComponent
  ],
  imports: [
    SharedModule,
    ClientRoutingModule
  ]
})
export class ClientModule { }
