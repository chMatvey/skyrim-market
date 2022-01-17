import {Component, Input} from '@angular/core';
import {Employee} from '@models/employee/employee';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.scss']
})
export class EmployeeListComponent {

  @Input()
  employees: Employee[]

  employeeUsername(employee: Employee): string {
    return employee.username
  }
}
