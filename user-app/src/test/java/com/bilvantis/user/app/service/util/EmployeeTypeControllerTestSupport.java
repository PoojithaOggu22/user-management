package com.bilvantis.user.app.service.util;

import com.bilvantis.user.data.model.EmployeeTypeDTO;

import java.util.Date;

public class EmployeeTypeControllerTestSupport {
    public static EmployeeTypeDTO createEmployeeTypeForNominatedByEmployeeDTO() {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(1L);
        employeeTypeDTO.setDesignation("technical manager");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("Pooja");
        employeeTypeDTO.setUpdatedBy("Pooja");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }

    public static EmployeeTypeDTO createEmployeeTypeForNomineeEmployeeDTO() {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(1L);
        employeeTypeDTO.setDesignation("programmer analyst");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("Pooja");
        employeeTypeDTO.setUpdatedBy("Pooja");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }

    public static EmployeeTypeDTO createEmployeeTypeDTO(Long id) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(id);
        employeeTypeDTO.setDesignation("Programmer Analyst");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("admin");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedBy("admin");
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }

    public static EmployeeTypeDTO updateEmployeeTypeDTO(Long id) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(id);
        employeeTypeDTO.setDesignation("Developer");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("admin");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedBy("admin");
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }
}
