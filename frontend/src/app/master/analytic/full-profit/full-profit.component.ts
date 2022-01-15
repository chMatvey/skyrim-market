import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'

@Component({
  selector: 'app-full-profit',
  templateUrl: './full-profit.component.html',
  styleUrls: ['./full-profit.component.scss']
})
export class FullProfitComponent implements OnInit {
  options: EChartsOption

  constructor() { }

  ngOnInit(): void {
    this.options = {
      title: {
        text: 'Full Profit'
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
          name: 'full profit',
          type: 'bar',
          data: [100, 200, 300]
        }
      ]
    }
  }
}
