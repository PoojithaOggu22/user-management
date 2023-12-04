package com.bilvantis.user.api.service;


import com.bilvantis.user.data.model.EmployeeDTO;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;


public interface EmployeeService <I extends EmployeeDTO, ID extends Serializable>{

    List<I> getManagersDirectAndIndirectReportees(ID managerId);

    I createEmployee(I employeeDTO);

    Page<I> getAllEmployees(Integer pageNumber, Integer size, String searchTerm);

    I getEmployeeById(ID id);

    I updateEmployeeById(ID id, I rewardsRequestDTO);

    void deleteEmployeeById(ID id);

}
