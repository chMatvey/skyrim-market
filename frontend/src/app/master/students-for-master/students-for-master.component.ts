import {Component, OnInit} from '@angular/core';
import {Student} from '@models/employee/student';
import {withLoading} from '@utils/loading-util';
import {MasterEmployeeService} from '@services/employee/master-employee.service'
import {Store} from '@ngxs/store'

@Component({
  selector: 'app-students-for-master',
  templateUrl: './students-for-master.component.html',
  styleUrls: ['./students-for-master.component.scss']
})
export class StudentsForMasterComponent implements OnInit {
  students: Student[]

  loading: boolean

  constructor(private employeeService: MasterEmployeeService) {
  }

  get noStudents(): boolean {
    return this.students?.length === 0
  }

  ngOnInit(): void {
    this.employeeService.getStudents()
      .pipe(withLoading(this))
      .subscribe(
        students => this.students = students,
        error => console.log(error)
      )
  }
}
