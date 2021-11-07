import { Component } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Entity } from '@models/entity'

@Component({
  template: ``
})
export abstract class OrderFormComponent {
  abstract form: FormGroup

  abstract disabled: boolean

  compareEntity(a: Entity, b: Entity): boolean {
    return a?.id === b?.id
  }
}
