import {Component, OnInit} from '@angular/core';
import {Employee} from '@models/employee/employee';
import {withLoading} from '@utils/loading-util';
import {MasterEmployeeService} from '@services/employee/master-employee.service'
import {Store} from '@ngxs/store'
import {MatDialog} from '@angular/material/dialog'
import {showError} from '@utils/notification-util'
import {toMessage} from '@utils/http-util'
import {Navigate} from '@ngxs/router-plugin'
import {FormControl} from '@angular/forms'
import {BaseComponent} from '@app/shared/base/base.component'
import {takeUntil} from 'rxjs/operators'
import {filterByRoleFunction, RoleFilter} from '@utils/filter-util'
import {Client} from '@state/client/client.actions'
import RemoveOrderMessagesById = Client.RemoveOrderMessagesById;

@Component({
  selector: 'app-employees-for-master',
  templateUrl: './employees-for-master.component.html',
  styleUrls: ['./employees-for-master.component.scss']
})
export class EmployeesForMasterComponent extends BaseComponent implements OnInit {
  employees: Employee[]
  filteredEmployees: Employee[]

  loading: boolean

  filterControl = new FormControl('Employees')

  constructor(private employeeService: MasterEmployeeService,
              private store: Store,
              private dialogService: MatDialog) {
    super()
  }

  get noEmployees(): boolean {
    return this.employees?.length === 0
  }

  ngOnInit(): void {
    this.employeeService.getEmployees()
      .pipe(withLoading(this))
      .subscribe(
        employees => {
          this.employees = employees
          this.filteredEmployees = [...employees]
        },
        error => showError(this.dialogService, toMessage(error))
      )

    this.filterControl.valueChanges.pipe(takeUntil(this.ngUnsubscribe))
        .subscribe((value: RoleFilter | RoleFilter[]) => {
          const filterFunc = filterByRoleFunction(value)
          this.filteredEmployees = this.employees.filter(filterFunc)
        })
  }

  openEmployee(id: number) {
    this.store.dispatch([
      new RemoveOrderMessagesById(id),
      new Navigate([`/client/order/${id}`])
    ])
  }
}
