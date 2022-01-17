import { Injectable } from '@angular/core'
import {Action, Selector, State, StateContext} from '@ngxs/store'
import {MasterEmployeeService} from "@services/employee/master-employee.service";
import {Order} from "@models/order/order";
import {ClientStateModel} from "@state/client/client.state";
import {Employee} from "@models/employee/employee";
import {Master} from "@state/master/master.actions";
import SetEmployeeType = Master.SetEmployeeType;
import {OrderTypeString} from "@models/order-type-string";
import {EmployeeTypeString} from "@models/employee-type-string";

export interface MasterStateModel<T extends Employee = Employee> {
  employee: T
}

const defaults: MasterStateModel = {
  employee: null
}

@State<MasterStateModel>({
  defaults,
  name: 'master'
})
@Injectable()
export class MasterState {

  @Selector()
  static employee(state: MasterStateModel): Employee | null {
    return state.employee
  }

  @Action(SetEmployeeType)
  setEmployeeType({patchState, getState}: StateContext<MasterStateModel>, {type}: SetEmployeeType) {
    const prevState = getState()
    const employee = {...prevState.employee, type}
    patchState({employee})
  }
}
