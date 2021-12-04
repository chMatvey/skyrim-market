import { Dropdown } from '@models/template/dropdown';

export function withEmptySelect<T>(values: Dropdown<T>[]): Dropdown<T>[] {
  return [
    {name: '-', value: null},
    ...values
  ]
}
