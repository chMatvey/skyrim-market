import {FormControl, FormGroup, Validators} from "@angular/forms";

export function createEmployeeCreateForm() {

  return new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null),
    confirmPassword: new FormControl(null, [Validators.required]),
    mentor: new FormControl(null, [Validators.required])
  })
}
