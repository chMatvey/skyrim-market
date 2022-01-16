import { UserRole } from '@models/user-role';
import { Toolbar } from '@models/template/toolbar';

export function getToolbarStateByUserRole(role: UserRole): Toolbar {
  switch (role) {
    case UserRole.CLIENT:
      return {
        logoRef: '/client',
        links: [
          {
            name: 'Make Order',
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
          },
          {
            name: 'Completed Orders',
            ref: 'completed-orders'
          }
        ]
      }
    case UserRole.MASTER:
      return {
        logoRef: '/master',
        links: [
          {
            name: 'Orders',
            ref: '/master/orders'
          },
          {
            name: 'Analytic',
            ref: 'analytic'
          },
          {
            name: 'Employees',
            ref: 'employees'
          },
          {
            name: 'Students',
            ref: 'students'
          }
        ]
      }
    default:
      return null
  }
}
