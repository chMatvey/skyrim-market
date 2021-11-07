import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { User } from '@models/user'
import { apiUrl } from '@app/app.const'

@Injectable({
  providedIn: 'root'
})
export class ContractorService {

  constructor(private http: HttpClient) { }

  all(): Observable<User[]> {
    return this.http.get<User[]>(`${apiUrl}/contractor`)
  }
}
