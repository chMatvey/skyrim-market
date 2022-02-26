import { UserRole } from '@models/user-role';
import { Toolbar } from '@models/template/toolbar';

export function getToolbarStateByUserRole(role: UserRole): Toolbar {
  switch (role) {
    case UserRole.CLIENT:
      return {
        logoRef: '/client',
        links: [
          {
            name: 'Создать заказ',
            ref: 'order',
            class: 'create_order'
          },
          {
            name: 'Мои заказы',
            ref: 'orders',
            class: 'my_orders'
          }
        ]
      }
    case UserRole.EMPLOYEE:
      return {
        logoRef: '/employee',
        links: [
          {
            name: 'Мои заказы',
            ref: 'my-orders',
            class: 'my_orders'
          },
          {
            name: 'Доступные заказы',
            ref: 'available-orders',
            class: 'available_orders'
          },
          {
            name: 'Выполненные заказы',
            ref: 'completed-orders',
            class: 'completed_orders'
          }
        ]
      }
    case UserRole.STUDENT:
      return {
        logoRef: '/student',
        links: [
          {
            name: 'Мои заказы',
            ref: 'my-orders',
            class: 'my_orders'
          },
          {
            name: 'Выполненные заказы',
            ref: 'completed-orders',
            class: 'completed_orders'
          }
        ]
      }
    case UserRole.MASTER:
      return {
        logoRef: '/master',
        links: [
          {
            name: 'Заказы',
            ref: '/master/orders',
            class: 'orders'
          },
          {
            name: 'Аналитика',
            ref: '/master/analytic',
            class: 'analytic'
          },
          {
            name: 'Сотрудники',
            ref: '/master/employees',
            class: 'employees'
          },
          {
            name: 'Студенты',
            ref: '/master/students',
            class: 'students'
          },
          {
            name: 'Создать сотрудника',
            ref: '/master/employees/create',
            class: 'create_employee'
          }
        ]
      }
    default:
      return null
  }
}
