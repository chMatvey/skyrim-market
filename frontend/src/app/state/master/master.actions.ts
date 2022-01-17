import {EmployeeTypeString} from "@models/employee-type-string";

export namespace Master {

  export class SetEmployeeType {
    static readonly type = '[Master] set employee type'
    constructor(public type: EmployeeTypeString) {}
  }
}
