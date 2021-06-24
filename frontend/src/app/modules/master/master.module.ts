import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { OrdersComponent } from './orders/orders.component';
import { ConfirmedOrderComponent } from './confirmed-order/confirmed-order.component';
import {MasterComponent} from "@modules/master/master.component";
import {MasterRoutingModule} from "@modules/master/master-routing.module";

@NgModule({
  declarations: [
    MasterComponent,
    OrdersComponent,
    ConfirmedOrderComponent
  ],
  imports: [
    SharedModule,
    MasterRoutingModule
  ]
})
export class MasterModule { }
