import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { MyOrdersComponent } from '@modules/employee/my-orders/my-orders.component';

const routes: Routes = [
  {
    path: '',
    component: MyOrdersComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
