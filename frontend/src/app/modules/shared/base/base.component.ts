import { Component, OnDestroy } from '@angular/core';
import { ReplaySubject } from 'rxjs';

@Component({
  template: ''
})
export class BaseComponent implements OnDestroy {

  private unsubscribe$ = new ReplaySubject<void>(1)

  get ngUnsubscribe() {
    return this.unsubscribe$.asObservable()
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next()
  }
}
