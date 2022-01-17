import { NgModule } from '@angular/core';
import { SharedModule } from '@app/shared/shared.module';
import { MyOrderComponent } from './my-order/my-order.component';
import { MyOrdersComponent } from "@app/student/my-orders/my-orders.component";
import { StudentRoutingModule } from "@app/student/student-routing.module";
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { EmployeeState } from '@state/employee/employee.state';
import { CompletedOrdersComponent } from './completed-orders/completed-orders.component';
import { CompletedOrderComponent } from './completed-order/completed-order.component'
import {StudentComponent} from "@app/student/student.component";

@NgModule({
  declarations: [
    StudentComponent,
    MyOrderComponent,
    MyOrdersComponent,
    CompletedOrdersComponent,
    CompletedOrderComponent
  ],
  imports: [
    SharedModule,
    StudentRoutingModule,
    NgxsModule.forFeature([AppState, EmployeeState])
  ]
})
export class StudentModule { }
