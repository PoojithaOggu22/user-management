package com.bilvantis.user.api.service;

import com.bilvantis.user.data.model.EmployeeDTO;

import java.io.Serializable;

public interface LoginService <I extends EmployeeDTO, ID extends Serializable> {

    EmployeeDTO verifyEmployeeLogin(String empId, String employeePassword);

}
