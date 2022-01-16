import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Employee } from '@models/employee/employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent {

  @Input()
  employees: Employee[]

  @Output()
  open = new EventEmitter<number>()

  employeeUsername(employee: Employee): string {
    return employee.username
  }
  openEmployee(id: number): void {
    this.open.emit(id)
  }
}
