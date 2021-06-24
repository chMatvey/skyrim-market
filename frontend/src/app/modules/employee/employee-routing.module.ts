import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { AvailableOrdersComponent } from "@modules/employee/available-orders/available-orders.component";
import { EmployeeComponent } from "@modules/employee/employee.component";
import { MyOrdersComponent } from "@modules/employee/my-orders/my-orders.component";
import { MyOrderComponent } from "@modules/employee/my-order/my-order.component";
import { AvailableOrderComponent } from "@modules/employee/available-order/available-order.component";
import { WelcomeComponent } from '@shared/welcome/welcome.component';

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
