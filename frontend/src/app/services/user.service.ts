import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '@models/user';
import { apiUrl } from '../app.const';
import { Observable } from 'rxjs';
import { createFormData } from '@utils/user-util'

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  login(user: User): Observable<User> {
    return this.http.post<User>(`${apiUrl}/login`, createFormData(user))
  }

  logout(): Observable<void> {
    return this.http.get<void>(`${apiUrl}/logout`)
  }

  register(user: User): Observable<User> {
    return this.http.post<User>(`${apiUrl}/user/client`, user)
  }
}
