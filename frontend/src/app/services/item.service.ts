import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Title } from '@models/title'
import { apiUrl } from '@app/app.const'
import { Item } from '@models/Item'

@Injectable({
  providedIn: 'root'
})
export class ItemService {

  constructor(private http: HttpClient) { }

  all(): Observable<Item[]> {
    return this.http.get<Item[]>(`${apiUrl}/item`)
  }
}
