import {Employee} from "@models/employee/employee";

export interface StudentForm extends Employee {
  mentorId?: number
}
