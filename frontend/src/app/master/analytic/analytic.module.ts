import { NgModule } from '@angular/core';
import { AnalyticComponent } from './analytic.component';
import { SharedModule } from '@app/shared/shared.module'
import { NgxsModule } from '@ngxs/store'
import { AppState } from '@state/app.state'
import { MasterState } from '@state/master/master.state'
import { AnalyticRoutingModule } from '@app/master/analytic/analytic-routing.module'
import { NgxEchartsModule } from 'ngx-echarts';
import { AverageProfitComponent } from './average-profit/average-profit.component';
import { FullProfitComponent } from './full-profit/full-profit.component';
import { PerformanceComponent } from './performance/performance.component'

@NgModule({
  declarations: [
    AnalyticComponent,
    AverageProfitComponent,
    FullProfitComponent,
    PerformanceComponent
  ],
  imports: [
    SharedModule,
    AnalyticRoutingModule,
    NgxsModule.forFeature([AppState, MasterState]),
    NgxEchartsModule.forRoot({
      echarts: () => import('echarts'),
    }),
  ]
})
export class AnalyticModule { }
