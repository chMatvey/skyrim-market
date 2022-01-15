import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'
import { AnalyticService } from '@services/analytic.service'
import { AverageProfitAnalytic } from '@models/analytic/average-profit-analytic'

@Component({
  selector: 'app-average-profit',
  templateUrl: './average-profit.component.html',
  styleUrls: ['./average-profit.component.scss']
})
export class AverageProfitComponent implements OnInit {
  options: EChartsOption

  constructor(private analyticService: AnalyticService) { }

  ngOnInit(): void {
    this.analyticService.getAverageProfit()
      .subscribe(result => this.initOptions(result))
  }

  private initOptions(model: AverageProfitAnalytic) {
    const orderTypes: string[] = model.forOrderTypes.map(data => data.orderType)
    const averageProfits: number[] = model.forOrderTypes.map(data => data.averageProfit)

    this.options = {
      title: {
        text: 'Average Profit'
      },
      tooltip: {
        trigger: "axis",
        axisPointer: {
          type: "shadow"
        }
      },
      xAxis: {
        data: orderTypes
      },
      yAxis: {},
      series: [
        {
          name: 'average profit',
          type: 'bar',
          data: averageProfits
        }
      ]
    }
  }
}
