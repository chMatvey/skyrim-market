import { HttpErrorResponse } from '@angular/common/http'

export function toMessage(errorResponse: HttpErrorResponse): string {
  return errorResponse.error.message || 'Server error'
}
