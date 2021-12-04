import { HttpErrorResponse } from '@angular/common/http'

export function toMessage(errorResponse: HttpErrorResponse) {
  return errorResponse.error.message
}
