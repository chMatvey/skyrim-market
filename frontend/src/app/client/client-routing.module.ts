import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ClientComponent } from './client.component';
import { TopProductsComponent } from './top-products/top-products.component';
import { OrderComponent } from './order/order.component';
import { OrdersComponent } from "@app/client/orders/orders.component";
import { ClientGuard } from '@guards/client.guard'

const routes: Routes = [
  {
    path: '',
    component: ClientComponent,
    canActivate: [ClientGuard],
    canActivateChild: [ClientGuard],
    children: [
      {
        path: '',
        component: TopProductsComponent
      },
      {
        path: 'order',
        component: OrderComponent,
      },
      {
        path: 'order/:id',
        component: OrderComponent,
      },
      {
        path: 'orders',
        component: OrdersComponent
      },
      {
        path: 'top-products',
        component: TopProductsComponent
      },
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule {
}
