import { FormControl, FormGroup, Validators } from "@angular/forms";
import { EmployeeTypeString } from '@models/employee-type-string'

export function employeeCreateForm(type: EmployeeTypeString = 'EMPLOYEE') {
  const formGroup =  new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null),
    confirmPassword: new FormControl(null, [Validators.required]),
  })
  if (type === 'STUDENT') {
    formGroup.addControl('mentor', new FormControl(null, [Validators.required]))
  }
  return formGroup
}
