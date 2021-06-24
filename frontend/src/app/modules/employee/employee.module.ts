import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { AvailableOrdersComponent } from './available-orders/available-orders.component';
import { AvailableOrderComponent } from './available-order/available-order.component';
import { MyOrderComponent } from './my-order/my-order.component';
import {EmployeeComponent} from "@modules/employee/employee.component";
import {MyOrdersComponent} from "@modules/employee/my-orders/my-orders.component";
import {EmployeeRoutingModule} from "@modules/employee/employee-routing.module";

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
