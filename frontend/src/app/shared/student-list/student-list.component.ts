import { Component, Input } from '@angular/core';
import { Student } from '@models/employee/student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent {

  @Input()
  students: Student[]

  studentUsername(student: Student): string {
    return student.username
  }
}
