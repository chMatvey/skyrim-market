import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { AvailableOrdersComponent } from './available-orders/available-orders.component';
import { AvailableOrderComponent } from './available-order/available-order.component';
import { MyOrderComponent } from './my-order/my-order.component';
import { EmployeeComponent } from "@app/employee/employee.component";
import { MyOrdersComponent } from "@app/employee/my-orders/my-orders.component";
import { EmployeeRoutingModule } from "@app/employee/employee-routing.module";

@NgModule({
  declarations: [
    EmployeeComponent,
    AvailableOrdersComponent,
    AvailableOrderComponent,
    MyOrderComponent,
    MyOrdersComponent
  ],
  imports: [
    SharedModule,
    EmployeeRoutingModule
  ]
})
export class EmployeeModule { }
