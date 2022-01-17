import {Employee} from "@models/employee/employee";

export interface Student extends Employee {
  mentor?: Employee
}
