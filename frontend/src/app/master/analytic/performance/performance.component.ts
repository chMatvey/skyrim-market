import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'
import { PerformanceAnalytic } from '@models/analytic/performance-analytic'
import { AnalyticService } from '@services/analytic.service'
import { orderStatusToString } from "@utils/order-status-util";

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss']
})
export class PerformanceComponent implements OnInit {
  options: EChartsOption

  constructor(private analyticService: AnalyticService) {
  }

  ngOnInit(): void {
    this.analyticService.getPerformance()
      .subscribe(result => this.initOptions(result))
  }

  initOptions(model: PerformanceAnalytic) {
    const data = model.orderStatusPercents.map(data => ({
      value: data.percent,
      name: orderStatusToString(data.status)
    }))

    this.options = {
      title: {
        text: 'Производительность',
        subtext: '%',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Результат %',
          type: 'pie',
          radius: '50%',
          data,
          // color: ['#2FDD92', '#f44336', 'yellow']
        }
      ]
    }
  }
}
