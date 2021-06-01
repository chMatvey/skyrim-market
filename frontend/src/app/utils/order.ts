import { Select } from '@models/select';

export function getOrderTypes(): Select<string>[] {
  return [
    {
      name: 'Pickpocketing',
      value: 'PICKPOCKETING'
    }
  ]
}
