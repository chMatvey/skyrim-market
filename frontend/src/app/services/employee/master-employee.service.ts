import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Employee } from '@models/employee/employee'
import { Student } from "@models/employee/student";
import { apiUrl } from '@app/app.const'

@Injectable({
  providedIn: 'root'
})
export class MasterEmployeeService {

  constructor(private httpClient: HttpClient) {}

  getEmployees(): Observable<Employee[]> {
    return this.httpClient.get<Employee[]>(`${apiUrl}/user/employee/`)
  }

  getStudents(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(`${apiUrl}/user/student/`)
  }

  setMentor(studentId: number, mentorId: number): Observable<Student> {
    return this.httpClient.get(`${apiUrl}/user/student/mentor/${studentId}/${mentorId}`)
  }
}
