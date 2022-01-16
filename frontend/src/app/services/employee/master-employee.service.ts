import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import { Observable } from 'rxjs'
import { Employee } from '@models/employee/employee'
import { Student } from "@models/employee/student";
import { apiUrl } from '@app/app.const'
import {Order} from "@models/order/order";

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

  getEmployee(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${apiUrl}/employee/${id}`)
  }

  getStudent(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${apiUrl}/student/${id}`)
  }

  setMentor(studentId: number, mentorId: number): Observable<Student> {
    return this.httpClient.get(`${apiUrl}/user/student/mentor/${studentId}/${mentorId}`)
  }
}
