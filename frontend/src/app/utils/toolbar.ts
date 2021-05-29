import { UserRole } from '../models/user-role';
import { ToolbarLink } from '../models/toolbar-link';

export function getToolbarLinksByUserRole(role: UserRole): ToolbarLink[] {
  switch (role) {
    case UserRole.CLIENT:
      return [
        {
          name: 'Make an Order',
          ref: 'order'
        },
        {
          name: 'My Orders',
          ref: 'orders'
        }
      ]
    default:
      throw new Error('Unknown Role')
  }
}
