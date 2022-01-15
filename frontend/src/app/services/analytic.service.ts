import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { apiUrl } from '@app/app.const'
import { AverageProfitAnalytic } from '@models/analytic/average-profit-analytic'
import { PerformanceAnalytic } from '@models/analytic/performance-analytic'
import { FullProfitAnalytic } from '@models/analytic/full-profit-analytic'

@Injectable({
  providedIn: 'root'
})
export class AnalyticService {

  constructor(private http: HttpClient) { }

  getAverageProfit(): Observable<AverageProfitAnalytic> {
    return this.http.get<AverageProfitAnalytic>(`${apiUrl}/analytic/average-profit`)
  }

  getFullProfit(): Observable<FullProfitAnalytic> {
    return this.http.get<FullProfitAnalytic>(`${apiUrl}/analytic/full-profit`)
  }

  getPerformance(): Observable<PerformanceAnalytic> {
    return this.http.get<PerformanceAnalytic>(`${apiUrl}/analytic/performance`)
  }
}
