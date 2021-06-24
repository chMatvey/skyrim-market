import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientGuard } from '@guards/client.guard';
import { EmployeeGuard } from '@guards/employee.guard';
import { MasterGuard } from '@guards/master.guard';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./modules/auth/auth.module')
      .then(module => module.AuthModule)
  },
  {
    path: 'client',
    loadChildren: () => import('./modules/client/client.module')
      .then(module => module.ClientModule),
    canLoad: [ClientGuard]
  },
  {
    path: 'employee',
    loadChildren: () => import('./modules/employee/employee.module')
      .then(module => module.EmployeeModule)
    ,
    canLoad: [EmployeeGuard]
  },
  {
    path: 'master',
    loadChildren: () => import('./modules/master/master.module')
      .then(module => module.MasterModule),
    canLoad: [MasterGuard]
  },
  {
    path: '**',
    redirectTo: ''
  }
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
