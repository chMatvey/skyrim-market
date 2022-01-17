import {Dropdown} from "@models/template/dropdown";
import {EmployeeTypeString} from "@models/employee-type-string";

export function getEmployeeTypes(): Dropdown<EmployeeTypeString>[] {
  return [
    {
      name: 'Student',
      value: 'STUDENT'
    },
    {
      name: 'Employee',
      value: 'EMPLOYEE'
    }
  ]
}
