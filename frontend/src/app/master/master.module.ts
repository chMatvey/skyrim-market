import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { OrdersForMasterComponent } from './orders-for-master/orders-for-master.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { MasterComponent } from "@app/master/master.component";
import { MasterRoutingModule } from "@app/master/master-routing.module";

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
