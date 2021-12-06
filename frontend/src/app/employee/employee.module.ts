import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { AvailableOrdersComponent } from './available-orders/available-orders.component';
import { AvailableOrderComponent } from './available-order/available-order.component';
import { MyOrderComponent } from './my-order/my-order.component';
import { EmployeeComponent } from "@app/employee/employee.component";
import { MyOrdersComponent } from "@app/employee/my-orders/my-orders.component";
import { EmployeeRoutingModule } from "@app/employee/employee-routing.module";
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { EmployeeState } from '@state/employee/employee.state';
import { CompletedOrdersComponent } from './completed-orders/completed-orders.component';
import { CompletedOrderComponent } from './completed-order/completed-order.component'

@NgModule({
  declarations: [
    EmployeeComponent,
    AvailableOrdersComponent,
    AvailableOrderComponent,
    MyOrderComponent,
    MyOrdersComponent,
    CompletedOrdersComponent,
    CompletedOrderComponent
  ],
  imports: [
    SharedModule,
    EmployeeRoutingModule,
    NgxsModule.forFeature([AppState, EmployeeState])
  ]
})
export class EmployeeModule { }
