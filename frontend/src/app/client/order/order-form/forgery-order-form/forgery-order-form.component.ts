import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms'
import { OrderFormComponent } from '@app/client/order/order-form/order-form.component'

@Component({
  selector: 'app-forgery-order-form',
  templateUrl: './forgery-order-form.component.html',
  styleUrls: [
    '../order-form.component.scss',
    './forgery-order-form.component.scss'
  ]
})
export class ForgeryOrderFormComponent extends OrderFormComponent implements OnInit {
  @Input()
  form: FormGroup

  @Input()
  disabled: boolean

  ngOnInit(): void {
  }
}
