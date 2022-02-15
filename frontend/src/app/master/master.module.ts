import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { OrdersForMasterComponent } from './orders-for-master/orders-for-master.component';
import { ConfirmOrderComponent } from './confirm-order/confirm-order.component';
import { MasterComponent } from "@app/master/master.component";
import { MasterRoutingModule } from "@app/master/master-routing.module";
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { MasterState } from '@state/master/master.state'
import { EmployeesForMasterComponent } from "@app/master/employees-for-master/employees-for-master.component";
import { StudentsForMasterComponent } from "@app/master/students-for-master/students-for-master.component";
import { CreateEmployeeComponent } from "@app/master/create-employee/create-employee.component";
import { MatCheckboxModule } from "@angular/material/checkbox";

@NgModule({
  declarations: [
    MasterComponent,
    OrdersForMasterComponent,
    EmployeesForMasterComponent,
    StudentsForMasterComponent,
    CreateEmployeeComponent,
    ConfirmOrderComponent
  ],
  imports: [
    SharedModule,
    MasterRoutingModule,
    NgxsModule.forFeature([AppState, MasterState]),
    MatCheckboxModule
  ]
})
export class MasterModule {
}
