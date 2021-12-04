import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ClientGuard } from '@guards/client.guard';
import { MasterGuard } from '@guards/master.guard'
import { EmployeeGuard } from '@guards/employee.guard'

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./auth/auth.module')
      .then(module => module.AuthModule)
  },
  {
    path: 'client',
    loadChildren: () => import('./client/client.module')
      .then(module => module.ClientModule),
    canLoad: [ClientGuard],
    canActivate: [ClientGuard]
  },
  {
    path: 'employee',
    loadChildren: () => import('./employee/employee.module')
      .then(module => module.EmployeeModule)
    ,
    canLoad: [EmployeeGuard],
    canActivate: [EmployeeGuard]
  },
  {
    path: 'master',
    loadChildren: () => import('./master/master.module')
      .then(module => module.MasterModule),
    canLoad: [MasterGuard],
    canActivate: [MasterGuard]
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
