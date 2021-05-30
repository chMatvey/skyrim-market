import { UserRole } from '../models/user-role';
import { Toolbar } from '../models/toolbar';

export function getToolbarStateByUserRole(role: UserRole): Toolbar {
  switch (role) {
    case UserRole.CLIENT:
      return {
        logoRef: '/client',
        links: [
          {
            name: 'Make an Order',
            ref: 'order'
          },
          {
            name: 'My Orders',
            ref: 'orders'
          }
        ]
      }
    default:
      return null
  }
}
