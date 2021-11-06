import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { BaseComponent } from '@app/shared/base/base.component';
import { Entity } from '@models/entity'

@Component({
  template: ``
})
export abstract class OrderFormComponent extends BaseComponent {
  abstract form: FormGroup

  abstract disabled: boolean

  compareEntity(a: Entity, b: Entity): boolean {
    return a?.id === b?.id
  }
}
