import { Dropdown } from '@models/dropdown';

export function withEmptySelect<T>(values: Dropdown<T>[]): Dropdown<T>[] {
  return [
    {name: '-', value: null},
    ...values
  ]
}
