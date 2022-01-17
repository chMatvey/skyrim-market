import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MyOrdersComponent } from "@app/student/my-orders/my-orders.component";
import { MyOrderComponent } from "@app/student/my-order/my-order.component";
import { WelcomeComponent } from '@app/shared/welcome/welcome.component';
import { CompletedOrdersComponent } from '@app/student/completed-orders/completed-orders.component'
import { CompletedOrderComponent } from '@app/student/completed-order/completed-order.component'
import {StudentComponent} from "@app/student/student.component";

const routes: Routes = [
  {
    path: '',
    component: StudentComponent,
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
        path: 'completed-orders',
        component: CompletedOrdersComponent
      },
      {
        path: 'completed-order/:id',
        component: CompletedOrderComponent
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule {
}
