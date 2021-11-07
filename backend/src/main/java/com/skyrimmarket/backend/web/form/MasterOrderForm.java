package com.skyrimmarket.backend.web.form;

import com.skyrimmarket.backend.model.user.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class MasterOrderForm {
    private final String comment;
    private final Employee contractor;
    private final Double price;
}
