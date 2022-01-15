import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'

@Component({
  selector: 'app-average-profit',
  templateUrl: './average-profit.component.html',
  styleUrls: ['./average-profit.component.scss']
})
export class AverageProfitComponent implements OnInit {
  options: EChartsOption

  constructor() { }

  ngOnInit(): void {
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
        data: ['Sweep', 'Forgery', 'Pickpocketing']
      },
      yAxis: {},
      series: [
        {
          name: 'average profit',
          type: 'bar',
          data: [100, 200, 300]
        }
      ]
    }
  }
}
