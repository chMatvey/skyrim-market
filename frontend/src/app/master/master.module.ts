import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { OrdersForMasterComponent } from './orders-for-master/orders-for-master.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { MasterComponent } from "@app/master/master.component";
import { MasterRoutingModule } from "@app/master/master-routing.module";
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { MasterState } from '@state/master/master.state'
import {EmployeesForMasterComponent} from "@app/master/employees-for-master/employees-for-master.component";

@NgModule({
  declarations: [
    MasterComponent,
    OrdersForMasterComponent,
    EmployeesForMasterComponent,
    ConfirmOrderComponent
  ],
  imports: [
    SharedModule,
    MasterRoutingModule,
    NgxsModule.forFeature([AppState, MasterState])
  ]
})
export class MasterModule { }
