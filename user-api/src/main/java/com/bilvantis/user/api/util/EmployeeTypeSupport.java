package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.EmployeeType;
import com.bilvantis.user.data.model.EmployeeTypeDTO;

public class EmployeeTypeSupport {
    public static EmployeeTypeDTO convertEmployeeTypeEntityToEmployeeTypeDto(EmployeeType employeeType) {
        EmployeeTypeDTO employeeTypeDTO = new EmployeeTypeDTO();
        employeeTypeDTO.setId(employeeType.getId());
        employeeTypeDTO.setDesignation(employeeType.getDesignation());
        employeeTypeDTO.setIsActive(employeeType.getIsActive());
        employeeTypeDTO.setCreatedBy(employeeType.getCreatedBy());
        employeeTypeDTO.setCreatedDate(employeeType.getCreatedDate());
        employeeTypeDTO.setUpdatedBy(employeeType.getUpdatedBy());
        employeeTypeDTO.setUpdatedDate(employeeType.getUpdatedDate());
        return employeeTypeDTO;
    }

    public static EmployeeType convertEmployeeTypeDtoToEmployeeTypeEntity(EmployeeTypeDTO employeeTypeDTO) {
        EmployeeType employeeType = new EmployeeType();
        employeeType.setId(employeeTypeDTO.getId());
        employeeType.setDesignation(employeeTypeDTO.getDesignation());
        employeeType.setIsActive(employeeTypeDTO.getIsActive());
        employeeType.setCreatedBy(employeeTypeDTO.getCreatedBy());
        employeeType.setCreatedDate(employeeTypeDTO.getCreatedDate());
        employeeType.setUpdatedBy(employeeTypeDTO.getUpdatedBy());
        employeeType.setUpdatedDate(employeeTypeDTO.getUpdatedDate());
        return employeeType;
    }

    public static void updateEmployeeTypeFromDTO(EmployeeType employeeType, EmployeeTypeDTO employeeTypeDTO) {
        employeeType.setId(employeeTypeDTO.getId());
        employeeType.setDesignation(employeeTypeDTO.getDesignation());
        employeeType.setIsActive(employeeTypeDTO.getIsActive());
        employeeType.setCreatedBy(employeeTypeDTO.getCreatedBy());
        employeeType.setUpdatedBy(employeeTypeDTO.getUpdatedBy());
        employeeType.setUpdatedDate(employeeTypeDTO.getUpdatedDate());

    }
}
