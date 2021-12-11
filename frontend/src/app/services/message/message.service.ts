import { Injectable } from '@angular/core';
import { Observable } from 'rxjs'
import { map, switchMap } from 'rxjs/operators';
import { HttpClient } from '@angular/common/http'
import { apiUrl } from '@app/app.const'
import { AngularFireMessaging } from '@angular/fire/messaging'


@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private messaging: AngularFireMessaging,
              private httpClient: HttpClient) {
  }

  requestPermission(): Observable<string> {
    return this.messaging.requestToken.pipe(
      switchMap(token => this.sendTokenToBackEnd(token))
    )
  }

  private sendTokenToBackEnd(token: string): Observable<string> {
    return this.httpClient.put<void>(`${apiUrl}/notification`, {token}).pipe(
      map(() => token)
    )
  }
}
