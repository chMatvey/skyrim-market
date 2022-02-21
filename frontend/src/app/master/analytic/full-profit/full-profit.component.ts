import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'
import { AnalyticService } from '@services/analytic.service'
import { FullProfitAnalytic } from '@models/analytic/full-profit-analytic'
import { OrderTypePipe } from "@app/shared/order-type/order-type.pipe";
import { orderTypeToString } from "@utils/order-type-util";

@Component({
  selector: 'app-full-profit',
  templateUrl: './full-profit.component.html',
  styleUrls: ['./full-profit.component.scss']
})
export class FullProfitComponent implements OnInit {
  options: EChartsOption

  constructor(private analyticService: AnalyticService) { }

  ngOnInit(): void {
    this.analyticService.getFullProfit()
      .subscribe(result => this.initOptions(result))
  }

  private initOptions(model: FullProfitAnalytic) {
    const orderTypes: string[] = model.forOrderTypes.map(data => orderTypeToString(data.orderType))
    const profits: number[] = model.forOrderTypes.map(data => data.fullProfit)

    this.options = {
      title: {
        text: 'Полная прибыль'
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
          name: 'полная прибыль',
          type: 'bar',
          data: profits
        }
      ]
    }
  }
}
