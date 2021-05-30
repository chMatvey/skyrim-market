import { Select } from '@models/select';

export function withEmptySelect<T>(values: Select<T>[]): Select<T>[] {
  return [
    {name: '-', value: null},
    ...values
  ]
}
