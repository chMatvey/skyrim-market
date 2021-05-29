import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { ClientComponent } from './client.component';
import { TopProductsComponent } from './top-products/top-products.component';

const routes: Routes = [
  {
    path: '',
    component: ClientComponent,
    children: [
      {
        path: '',
        component: TopProductsComponent
      }
    ]
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ClientRoutingModule { }
