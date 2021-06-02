import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { apiUrl } from '@app/app.const';

@Injectable()
export class TitleService {

  constructor(private http: HttpClient) { }

  all(): Observable<string[]> {
    return this.http.get<string[]>(`${apiUrl}/title`)
  }
}
