package com.skyrimmarket.backend.web.form;

import com.skyrimmarket.backend.model.user.Employee;
import com.skyrimmarket.backend.model.user.Student;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class StudentForm {
    Student student;
    Employee mentor;
}
