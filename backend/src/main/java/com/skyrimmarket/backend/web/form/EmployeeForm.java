package com.skyrimmarket.backend.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.annotation.Nullable;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class EmployeeForm {
    String username;
    String password;
    Boolean isStudent = false;
    @Nullable
    Long mentorId;
}
