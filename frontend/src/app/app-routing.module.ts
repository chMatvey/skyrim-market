import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  {
    path: '',
    loadChildren: () => import('./modules/auth/auth.module')
      .then(module => module.AuthModule)
  },
  {
    path: 'client',
    loadChildren: () => import('./modules/client/client.module')
      .then(module => module.ClientModule)
  },
  {
    path: 'employee',
    loadChildren: () => import('./modules/employee/employee.module')
      .then(module => module.EmployeeModule)
  },
  {
    path: 'master',
    loadChildren: () => import('./modules/master/master.module')
      .then(module => module.MasterModule)
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
