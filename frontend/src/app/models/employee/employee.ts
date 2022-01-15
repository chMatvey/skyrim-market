import {User} from "@models/user";

export interface Employee extends User {
  isStudent?: boolean
}
