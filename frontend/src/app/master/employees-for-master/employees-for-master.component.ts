import {Component, OnInit} from '@angular/core';
import {Employee} from '@models/employee/employee';
import {withLoading} from '@utils/loading-util';
import {MasterEmployeeService} from '@services/employee/master-employee.service'
import {Store} from '@ngxs/store'
import {Navigate} from '@ngxs/router-plugin'

@Component({
  selector: 'app-employees-for-master',
  templateUrl: './employees-for-master.component.html',
  styleUrls: ['./employees-for-master.component.scss']
})
export class EmployeesForMasterComponent implements OnInit {
  employees: Employee[]

  loading: boolean

  constructor(private employeeService: MasterEmployeeService) {
  }

  get noEmployees(): boolean {
    return this.employees?.length === 0
  }

  ngOnInit(): void {
    this.employeeService.getEmployees()
      .pipe(withLoading(this))
      .subscribe(
        employees => this.employees = employees,
        error => console.log(error)
      )
  }
}
