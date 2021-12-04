import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AvailableOrdersComponent } from "@app/employee/available-orders/available-orders.component";
import { EmployeeComponent } from "@app/employee/employee.component";
import { MyOrdersComponent } from "@app/employee/my-orders/my-orders.component";
import { MyOrderComponent } from "@app/employee/my-order/my-order.component";
import { AvailableOrderComponent } from "@app/employee/available-order/available-order.component";
import { WelcomeComponent } from '@app/shared/welcome/welcome.component';

const routes: Routes = [
  {
    path: '',
    component: EmployeeComponent,
    children: [
      {
        path: '',
        component: WelcomeComponent
      },
      {
        path: 'my-orders',
        component: MyOrdersComponent
      },
      {
        path: 'my-order/:id',
        component: MyOrderComponent
      },
      {
        path: 'available-orders',
        component: AvailableOrdersComponent
      },
      {
        path: 'available-order/:id',
        component: AvailableOrderComponent
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EmployeeRoutingModule {
}
