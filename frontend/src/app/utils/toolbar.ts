import { UserRole } from '@models/user-role';
import { Toolbar } from '@models/toolbar';

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
    case UserRole.EMPLOYEE:
      return {
        logoRef: '/employee',
        links: [
          {
            name: 'My Orders',
            ref: 'my-orders'
          },
          {
            name: 'Available Orders',
            ref: 'available-orders'
          }
        ]
      }
    default:
      return null
  }
}
