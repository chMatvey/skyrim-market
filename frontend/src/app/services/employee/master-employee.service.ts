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

  get(id: number): Observable<Employee> {
    return this.httpClient.get<Employee>(`${apiUrl}/employee/${id}`)
  }

  createEmployee(employee: Employee): Observable<Employee> {
    return this.httpClient.post<Employee>(`${apiUrl}/user/employee`, employee)
  }
}
