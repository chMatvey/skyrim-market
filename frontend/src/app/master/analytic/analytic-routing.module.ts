import { RouterModule, Routes } from '@angular/router'
import { AnalyticComponent } from '@app/master/analytic/analytic.component'
import { NgModule } from '@angular/core'

const routes: Routes = [
  {
    path: '',
    component: AnalyticComponent
  }
]

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AnalyticRoutingModule {

}
