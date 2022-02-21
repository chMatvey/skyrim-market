import { UserRole } from '@models/user-role';
import { Toolbar } from '@models/template/toolbar';

export function getToolbarStateByUserRole(role: UserRole): Toolbar {
  switch (role) {
    case UserRole.CLIENT:
      return {
        logoRef: '/client',
        links: [
          {
            name: 'Сделать заказ',
            ref: 'order'
          },
          {
            name: 'Мои заказы',
            ref: 'orders'
          }
        ]
      }
    case UserRole.EMPLOYEE:
      return {
        logoRef: '/employee',
        links: [
          {
            name: 'Мои заказы',
            ref: 'my-orders'
          },
          {
            name: 'Доступные заказы',
            ref: 'available-orders'
          },
          {
            name: 'Выполненные заказы',
            ref: 'completed-orders'
          }
        ]
      }
    case UserRole.STUDENT:
      return {
        logoRef: '/student',
        links: [
          {
            name: 'Мои заказы',
            ref: 'my-orders'
          },
          {
            name: 'Выполненные заказы',
            ref: 'completed-orders'
          }
        ]
      }
    case UserRole.MASTER:
      return {
        logoRef: '/master',
        links: [
          {
            name: 'Заказы',
            ref: '/master/orders'
          },
          {
            name: 'Аналитика',
            ref: '/master/analytic'
          },
          {
            name: 'Сотрудники',
            ref: '/master/employees'
          },
          {
            name: 'Студенты',
            ref: '/master/students'
          },
          {
            name: 'Создать сотрудника',
            ref: '/master/employees/create'
          }
        ]
      }
    default:
      return null
  }
}
