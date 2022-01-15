import { Component, OnInit } from '@angular/core';
import { EChartsOption } from 'echarts'

@Component({
  selector: 'app-performance',
  templateUrl: './performance.component.html',
  styleUrls: ['./performance.component.scss']
})
export class PerformanceComponent implements OnInit {
  options: EChartsOption

  constructor() {
  }

  ngOnInit(): void {
    this.options = {
      title: {
        text: 'Performance',
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
          name: 'Result %',
          type: 'pie',
          radius: '50%',
          data: [
            { value: 80, name: 'Completed' },
            { value: 10, name: 'Failed' },
            { value: 10, name: 'Canceled' },
          ],
          color: ['#2FDD92', '#f44336', 'yellow']
        }
      ]
    }
  }
}
