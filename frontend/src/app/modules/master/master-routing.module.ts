import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {OrdersComponent} from "@modules/client/orders/orders.component";
import {ConfirmedOrderComponent} from "@modules/master/confirmed-order/confirmed-order.component";
import {MasterComponent} from "@modules/master/master.component";

const routes: Routes = [
  {
    path: '',
    component: MasterComponent,
    children: [
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'confirmed-order',
        component: ConfirmedOrderComponent
      }
    ]
  },
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MasterRoutingModule { }
