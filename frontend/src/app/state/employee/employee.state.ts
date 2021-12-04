import { Injectable } from '@angular/core'
import { State } from '@ngxs/store'

export interface EmployeeStateModel {}

const defaults: EmployeeStateModel = {}

@State<EmployeeStateModel>({
  defaults,
  name: 'employee'
})
@Injectable()
export class EmployeeState {}
