import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ConfirmOrderComponent } from "@app/master/confirm-order/confirm-order.component";
import { MasterComponent } from "@app/master/master.component";
import { OrdersForMasterComponent } from '@app/master/orders-for-master/orders-for-master.component';
import { EmployeesForMasterComponent } from '@app/master/employees-for-master/employees-for-master.component';
import { WelcomeComponent } from '@app/shared/welcome/welcome.component';
import {StudentsForMasterComponent} from "@app/master/students-for-master/students-for-master.component";

const routes: Routes = [
  {
    path: '',
    component: MasterComponent,
    children: [
      {
        path: '',
        component: WelcomeComponent
      },
      {
        path: 'orders',
        component: OrdersForMasterComponent
      },
      {
        path: 'order/:id',
        component: ConfirmOrderComponent
      },
      {
        path: 'analytic',
        loadChildren: () => import('./analytic/analytic.module')
          .then(module => module.AnalyticModule)
      },
      {
        path: 'employees',
        component: EmployeesForMasterComponent
      },
      {
        path: 'employee/:id',
        component: ConfirmOrderComponent
      },
      {
        path: 'students',
        component: StudentsForMasterComponent
      },
      {
        path: 'student/:id',
        component: ConfirmOrderComponent
      }
    ]
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterRoutingModule {
}
