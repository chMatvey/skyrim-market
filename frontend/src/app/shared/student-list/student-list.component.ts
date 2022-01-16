import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Student } from '@models/employee/student';

@Component({
  selector: 'app-student-list',
  templateUrl: './student-list.component.html',
  styleUrls: ['./student-list.component.scss']
})
export class StudentListComponent {

  @Input()
  students: Student[]

  @Output()
  open = new EventEmitter<number>()

  studentUsername(student: Student): string {
    return student.username
  }

  openStudent(id: number): void {
    this.open.emit(id)
  }
}
