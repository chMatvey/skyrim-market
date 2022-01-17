import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Student} from "@models/employee/student";
import {apiUrl} from "@app/app.const";

@Injectable({
  providedIn: 'root'
})
export class EmployeeStudentService {

  constructor(private httpClient: HttpClient) {}

  getStudents(): Observable<Student[]> {
    return this.httpClient.get<Student[]>(`${apiUrl}/user/employee/students`)
  }
}
