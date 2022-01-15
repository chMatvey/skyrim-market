import {Employee} from "@models/employee/employee";

export interface Student extends Employee {
  mentorId?: number
}
