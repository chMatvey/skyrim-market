import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { apiUrl } from '@app/app.const';
import { Title } from '@models/title'

@Injectable({
  providedIn: 'root'
})
export class TitleService {

  constructor(private http: HttpClient) { }

  all(): Observable<Title[]> {
    return this.http.get<Title[]>(`${apiUrl}/title`)
  }
}
