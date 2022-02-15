import { Component, OnInit } from "@angular/core";
import { BaseComponent } from "@app/shared/base/base.component";
import { Employee } from "@models/employee/employee";
import { FormGroup } from "@angular/forms";
import { Dropdown } from "@models/template/dropdown";
import { ActivatedRoute } from "@angular/router";
import { Store } from "@ngxs/store";
import { MatDialog } from "@angular/material/dialog";
import { MasterEmployeeService } from "@services/employee/master-employee.service";
import { tap, withLatestFrom } from "rxjs/operators";
import { withLoading } from "@utils/loading-util";
import { showError, showNotification } from "@utils/notification-util";
import { toMessage } from "@utils/http-util";
import { Navigate } from "@ngxs/router-plugin";
import { MasterState } from "@state/master/master.state";
import { EmployeeTypeString } from "@models/employee-type-string";
import { getEmployeeTypes } from "@utils/employee-type-util";
import { createEmployeeCreateForm } from "@utils/employee-util";
import { Master } from "@state/master/master.actions";
import SetEmployeeType = Master.SetEmployeeType;
import { StudentForm } from "@models/employee/student-form";
import { UserRole } from "@models/user-role";

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.scss']
})
export class CreateEmployeeComponent extends BaseComponent implements OnInit {
  employeeForm: FormGroup
  employee: Employee

  employees: Employee[] = []

  employeeTypes: Dropdown<EmployeeTypeString>[] = getEmployeeTypes()
  employeeType: EmployeeTypeString = null

  loading: boolean
  compareEntity: any;

  constructor(private activateRoute: ActivatedRoute,
              private store: Store,
              private dialogService: MatDialog,
              private employeeService: MasterEmployeeService) {
    super();
  }

  ngOnInit() {
    this.employeeForm = createEmployeeCreateForm()

    this.employeeService.getEmployees().subscribe(employees => this.employees = employees)
  }

  close(): void {
    this.store.dispatch(new Navigate(['/master']))
  }

  create() {

    let employee;

    if (this.employeeType == "STUDENT") {
      employee = this.employeeForm.value as StudentForm
      employee.role = UserRole.STUDENT
      employee.mentorId = this.employeeForm.value.mentor.id
      employee.isStudent = true
    } else {
      employee = this.employeeForm.value as Employee
      employee.isStudent = false
    }

    if (employee.password !== employee.confirmPassword) {
      showNotification(this.dialogService, 'Passwords not equals')
      return;
    }

    this.employeeService.createEmployee(employee)
      .pipe(
        withLoading(this),
        tap(() => showNotification(this.dialogService, 'Employee successfully created!'))
      )
      .subscribe(
        () => this.store.dispatch(new Navigate(['/master'])),
        error => showError(this.dialogService, toMessage(error))
      )
  }

  get mentorInvalid(): boolean {
    if (this.employeeType == "STUDENT") {
      return this.employeeForm.get('mentor').invalid
    } else {
      return false
    }
  }

  get usernameInvalid(): boolean {
    return this.employeeForm.get('username').invalid
  }

  onEmployeeTypeChange(value: EmployeeTypeString) {
    this.employeeType = value
    this.store.dispatch(new SetEmployeeType(value))
      .pipe(withLatestFrom(this.store.select(MasterState.employee)))
      .subscribe(([, employee]) => this.employeeForm = createEmployeeCreateForm())
  }
}
