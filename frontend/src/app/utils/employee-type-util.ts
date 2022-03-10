import { Dropdown } from "@models/template/dropdown";
import { EmployeeTypeString } from "@models/employee-type-string";

export function getEmployeeTypes(): Dropdown<EmployeeTypeString>[] {
  return [
    {
      name: 'Ученик гильдии воров',
      value: 'STUDENT'
    },
    {
      name: 'Член гильдии воров',
      value: 'EMPLOYEE'
    }
  ]
}
