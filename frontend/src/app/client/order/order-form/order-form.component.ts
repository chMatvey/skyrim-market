import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BaseComponent } from '@app/shared/base/base.component';

@Component({
  template: ``
})
export abstract class OrderFormComponent extends BaseComponent {
  abstract form: FormGroup

  abstract disabled: boolean
}
