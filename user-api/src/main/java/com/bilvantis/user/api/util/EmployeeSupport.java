package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.Employee;
import com.bilvantis.user.data.model.EmployeeDTO;
import org.springframework.util.ObjectUtils;
import java.util.Base64;

import static com.bilvantis.user.api.util.EmployeeTypeSupport.convertEmployeeTypeDtoToEmployeeTypeEntity;
import static com.bilvantis.user.api.util.EmployeeTypeSupport.convertEmployeeTypeEntityToEmployeeTypeDto;
import static com.bilvantis.user.api.util.TeamSupport.convertTeamDtoToTeamEntity;
import static com.bilvantis.user.api.util.TeamSupport.convertTeamEntityToTeamDto;

public class EmployeeSupport {

    public static EmployeeDTO convertEmployeeEntityToEmployeeDto(Employee employee) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setPhoneNumber(employee.getPhoneNumber());
        employeeDto.setTeam(convertTeamEntityToTeamDto(employee.getTeam()));
        employeeDto.setEmployeeType(convertEmployeeTypeEntityToEmployeeTypeDto(employee.getEmployeeType()));
        employeeDto.setJoiningDate(employee.getJoiningDate());
        employeeDto.setEmployeeId(employee.getEmployeeId());
        employeeDto.setManagerId(employee.getManagerId());
        employeeDto.setPassword(employee.getPassword());
        employeeDto.setIsActive(employee.getIsActive());
        employeeDto.setFirstTimeUser(employee.getFirstTimeUser());
        employeeDto.setIsAdmin(employee.getIsAdmin());
        employeeDto.setOtp(employee.getOtp());
        employeeDto.setOtpGenerationTime(employee.getOtpGenerationTime());
        employeeDto.setCreatedBy(employee.getCreatedBy());
        employeeDto.setCreatedDate(employee.getCreatedDate());
        employeeDto.setUpdatedBy(employee.getUpdatedBy());
        employeeDto.setUpdatedDate(employee.getUpdatedDate());
        return employeeDto;
    }

    public static Employee convertEmployeeDtoToEmployeeEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setId(employeeDTO.getId());
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setPhoneNumber(employeeDTO.getPhoneNumber());
        employee.setTeam(convertTeamDtoToTeamEntity(employeeDTO.getTeam()));
        employee.setEmployeeType(convertEmployeeTypeDtoToEmployeeTypeEntity(employeeDTO.getEmployeeType()));
        employee.setJoiningDate(employeeDTO.getJoiningDate());
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setManagerId(employeeDTO.getManagerId());
        employee.setPassword(employeeDTO.getPassword());
        employee.setIsActive(employeeDTO.getIsActive());
        employee.setIsAdmin(employeeDTO.getIsAdmin());
        employee.setFirstTimeUser(employeeDTO.getFirstTimeUser());
        employee.setOtp(employeeDTO.getOtp());
        employee.setOtpGenerationTime(ObjectUtils.isEmpty(employeeDTO.getOtpGenerationTime()) ? null: employeeDTO.getOtpGenerationTime());
        employee.setCreatedBy(employeeDTO.getCreatedBy());
        employee.setCreatedDate(employeeDTO.getCreatedDate());
        employee.setUpdatedBy(employeeDTO.getUpdatedBy());
        employee.setUpdatedDate(employeeDTO.getUpdatedDate());
        return employee;
    }

    public static String encodingPassword(String encodingPassword) {
        return Base64.getEncoder().encodeToString(encodingPassword.getBytes());
    }

    public static String decodingPassword(String decodingPassword) {
        byte[] decodedBytesPassword = Base64.getDecoder().decode(decodingPassword);
        return new String(decodedBytesPassword);
    }

}
