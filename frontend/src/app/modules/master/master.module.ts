import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { OrdersForMasterComponent } from './orders-for-master/orders-for-master.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { MasterComponent } from "@modules/master/master.component";
import { MasterRoutingModule } from "@modules/master/master-routing.module";

@NgModule({
  declarations: [
    MasterComponent,
    OrdersForMasterComponent,
    ConfirmOrderComponent
  ],
  imports: [
    SharedModule,
    MasterRoutingModule
  ]
})
export class MasterModule { }
