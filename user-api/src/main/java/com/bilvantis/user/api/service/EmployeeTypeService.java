package com.bilvantis.user.api.service;

import com.bilvantis.user.data.model.EmployeeTypeDTO;

import java.io.Serializable;
import java.util.List;

public interface EmployeeTypeService<I extends EmployeeTypeDTO,ID extends Serializable> {

    I createEmployeeType(I employeeTypeDTO);

    List<I> getAllEmployeeTypes();

    I getEmployeeTypeById(ID id);

    I updateEmployeeType(ID id, I employeeTypeDTO);

    void deleteEmployeeType(ID id);
}
