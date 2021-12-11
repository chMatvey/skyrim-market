import { ClientStateModel } from '@state/client/client.state'
import { localStorageOrderMessagesField } from '@app/app.const'

export function clientInitialState(): ClientStateModel {
  return {
    order: null,
    orderMessages: JSON.parse(localStorage.getItem(localStorageOrderMessagesField)) ?? []
  }
}
