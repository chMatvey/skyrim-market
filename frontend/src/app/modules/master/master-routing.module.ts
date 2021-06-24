import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ConfirmOrderComponent } from "@modules/master/confirm-order/confirm-order.component";
import { MasterComponent } from "@modules/master/master.component";
import { OrdersForMasterComponent } from '@modules/master/orders-for-master/orders-for-master.component';
import { WelcomeComponent } from '@shared/welcome/welcome.component';

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
        path: 'confirm-order/:id',
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
