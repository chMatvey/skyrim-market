import { FormControl, FormGroup, Validators } from '@angular/forms';

export function createLoginForm() {
  return new FormGroup({
    username: new FormControl(null, [Validators.required]),
    password: new FormControl(null, [Validators.required])
  })
}
