package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.EmployeeType;
import com.bilvantis.user.data.model.EmployeeTypeDTO;

import java.util.Date;

public class EmployeeTypeTestSupport {

    public static EmployeeTypeDTO createEmployeeTypeDTO(Long id) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(id);
        employeeTypeDTO.setDesignation("Senior Programmer Analyst");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("SYSTEM");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedBy("SYSTEM");
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }

    public static EmployeeTypeDTO updateEmployeeTypeDTO(Long id) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(id);
        employeeTypeDTO.setDesignation(" Programmer Analyst");
        employeeTypeDTO.setIsActive(true);
        employeeTypeDTO.setCreatedBy("admin");
        employeeTypeDTO.setCreatedDate(new Date());
        employeeTypeDTO.setUpdatedBy("admin");
        employeeTypeDTO.setUpdatedDate(new Date());
        return employeeTypeDTO;
    }

    public static EmployeeType createEmployeeType(Long id) {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setId(id);
        employeeType.setDesignation("Senior Programmer Analyst");
        employeeType.setIsActive(true);
        employeeType.setCreatedBy("SYSTEM");
        employeeType.setCreatedDate(new Date());
        employeeType.setUpdatedBy("SYSTEM");
        employeeType.setUpdatedDate(new Date());
        return employeeType;
    }

    public static EmployeeType createEmployeeTypeIsActiveFalse(Long id) {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setId(id);
        employeeType.setDesignation("Senior Programmer Analyst");
        employeeType.setIsActive(false);
        employeeType.setCreatedBy("SYSTEM");
        employeeType.setCreatedDate(new Date());
        employeeType.setUpdatedBy("SYSTEM");
        employeeType.setUpdatedDate(new Date());
        return employeeType;
    }
}
