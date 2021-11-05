import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms'
import { Observable } from 'rxjs'
import { Title } from '@models/title'
import { TitleService } from '@services/title.service'
import { OrderFormComponent } from '@app/client/order/order-form/order-form.component'

@Component({
  selector: 'app-sweep-order-form',
  templateUrl: './sweep-order-form.component.html',
  styleUrls: [
    '../order-form.component.scss',
    './sweep-order-form.component.scss'
  ]
})
export class SweepOrderFormComponent extends OrderFormComponent implements OnInit {
  @Input()
  form: FormGroup

  @Input()
  disabled: boolean

  titles$: Observable<Title[]>

  constructor(private titleService: TitleService) {
    super()
  }

  ngOnInit(): void {
    this.titles$ = this.titleService.all()
  }
}
