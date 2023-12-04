package com.bilvantis.user.api.util;

import com.bilvantis.user.dao.data.model.Employee;
import com.bilvantis.user.data.model.EmployeeDTO;

import java.util.Date;

import static com.bilvantis.user.api.util.EmployeeTypeTestSupport.createEmployeeType;
import static com.bilvantis.user.api.util.EmployeeTypeTestSupport.createEmployeeTypeDTO;
import static com.bilvantis.user.api.util.RewardsApiTestConstant.*;
import static com.bilvantis.user.api.util.TeamTestSupport.*;

public class EmployeeTestSupport {

    public static Employee createEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(false);
        employee.setOtp("1234");
        employee.setOtpGenerationTime(12345L);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static Employee createEmployeeForOtpDetails(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(false);
        employee.setOtp("9999");
        long currentMillis = System.currentTimeMillis();
        Long otpGeneratedMillis = currentMillis - 1690000; // Set it within the last 30 minutes
        employee.setOtpGenerationTime(otpGeneratedMillis);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static Employee createEmployeeForOtpDetailsOtpGenerationNull(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(false);
        employee.setOtp("9999");
        employee.setOtpGenerationTime(null);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static Employee createEmployeeForOtpMismatch(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(false);
        employee.setOtp("12345");
        long currentMillis = System.currentTimeMillis();
        Long otpGeneratedMillis = currentMillis - 1690000; // Set it within the last 30 minutes
        employee.setOtpGenerationTime(otpGeneratedMillis);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static EmployeeDTO createEmployeeDTOForOtpDetails(Long id) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(id);
        employeeDto.setFirstName("arjun");
        employeeDto.setLastName("pula");
        employeeDto.setEmail("test.hello@bivantis.io");
        employeeDto.setPhoneNumber("0987654321");
        employeeDto.setTeam(createTeamDTO(1L));
        employeeDto.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDto.setJoiningDate(new Date());
        employeeDto.setEmployeeId(EMP_ID);
        employeeDto.setManagerId(1L);
        employeeDto.setPassword(NEW_PASSWORD);
        employeeDto.setIsActive(true);
        employeeDto.setFirstTimeUser(false);
        employeeDto.setIsAdmin(false);
        employeeDto.setOtp(null);
        employeeDto.setOtpGenerationTime(null);
        employeeDto.setCreatedBy("admin");
        employeeDto.setCreatedDate(new Date());
        employeeDto.setUpdatedBy("admin");
        employeeDto.setUpdatedDate(new Date());
        return employeeDto;
    }

    public static EmployeeDTO createEmployeeDTO(Long id) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(id);
        employeeDto.setFirstName("arjun");
        employeeDto.setLastName("pula");
        employeeDto.setEmail("test.hello@bivantis.io");
        employeeDto.setPhoneNumber("0987654321");
        employeeDto.setTeam(createTeamDTO(1L));
        employeeDto.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDto.setJoiningDate(new Date());
        employeeDto.setEmployeeId(EMP_ID);
        employeeDto.setManagerId(1L);
        employeeDto.setPassword(NEW_PASSWORD);
        employeeDto.setIsActive(true);
        employeeDto.setFirstTimeUser(false);
        employeeDto.setIsAdmin(false);
        employeeDto.setOtp("678819");
        employeeDto.setOtpGenerationTime(1698933920090L);
        employeeDto.setCreatedBy("admin");
        employeeDto.setCreatedDate(new Date());
        employeeDto.setUpdatedBy("admin");
        employeeDto.setUpdatedDate(new Date());
        return employeeDto;
    }
    public static EmployeeDTO createAdminEmployeeDTO(Long id) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        employeeDto.setId(id);
        employeeDto.setFirstName("arjun");
        employeeDto.setLastName("pula");
        employeeDto.setEmail("test.hello@bivantis.io");
        employeeDto.setPhoneNumber("0987654321");
        employeeDto.setTeam(createTeamDTO(1L));
        employeeDto.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDto.setJoiningDate(new Date());
        employeeDto.setEmployeeId(EMP_ID);
        employeeDto.setManagerId(1L);
        employeeDto.setPassword(EMP_PASSWORD);
        employeeDto.setIsActive(true);
        employeeDto.setFirstTimeUser(true);
        employeeDto.setIsAdmin(true);
        employeeDto.setOtp("1234");
        employeeDto.setOtpGenerationTime(12345L);
        employeeDto.setCreatedBy("admin");
        employeeDto.setCreatedDate(new Date());
        employeeDto.setUpdatedBy("admin");
        employeeDto.setUpdatedDate(new Date());
        return employeeDto;
    }
    public static Employee createAdminEmployee(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(true);
        employee.setOtp("1234");
        employee.setOtpGenerationTime(12345L);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static Employee updateEmployeeData(Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName("arjun");
        employee.setLastName("pula");
        employee.setEmail("test.hello@bivantis.io");
        employee.setPhoneNumber("0987654321");
        employee.setTeam(createTeam(1L));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId(EMP_ID);
        employee.setManagerId(1L);
        employee.setPassword("MTIzNDU2");
        employee.setIsActive(true);
        employee.setFirstTimeUser(true);
        employee.setIsAdmin(false);
        employee.setOtp("1234");
        employee.setOtpGenerationTime(12345L);
        employee.setCreatedBy("admin");
        employee.setCreatedDate(new Date());
        employee.setUpdatedBy("admin");
        employee.setUpdatedDate(new Date());
        return employee;
    }

    public static EmployeeDTO updateEmployeeDTOData(Long id) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(id);
        employeeDTO.setFirstName("arjun");
        employeeDTO.setLastName("pula");
        employeeDTO.setEmail("test.hello@bivantis.io");
        employeeDTO.setPhoneNumber("0987654321");
        employeeDTO.setTeam(createTeamDTO(1L));
        employeeDTO.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDTO.setJoiningDate(new Date());
        employeeDTO.setEmployeeId(EMP_ID);
        employeeDTO.setManagerId(1L);
        employeeDTO.setPassword("MTIzNDU2");
        employeeDTO.setIsActive(true);
        employeeDTO.setFirstTimeUser(true);
        employeeDTO.setIsAdmin(false);
        employeeDTO.setOtp("1234");
        employeeDTO.setOtpGenerationTime(12345L);
        employeeDTO.setCreatedBy("admin");
        employeeDTO.setCreatedDate(new Date());
        employeeDTO.setUpdatedBy("admin");
        employeeDTO.setUpdatedDate(new Date());
        return employeeDTO;
    }
    public  static Employee createNominatedByEmployee(Long id){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("jani");
        employee.setLastName("shaik");
        employee.setEmail("jani@gmail.com");
        employee.setPhoneNumber("9286890544");
        employee.setJoiningDate(new Date());
        employee.setEmployeeId("BT100");
        employee.setManagerId(3L);
        employee.setPassword("123");
        employee.setFirstTimeUser(true);
        employee.setOtp("224");
        employee.setOtpGenerationTime(4L);
        employee.setIsAdmin(true);
        employee.setIsActive(true);
        employee.setCreatedBy("Pooja");
        employee.setUpdatedBy("Pooja");
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());
        return employee;
    }
    public static Employee createNomineeEmployee(){
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("pooja");
        employee.setLastName("oggu");
        employee.setEmail("pooja@gmail.com");
        employee.setPhoneNumber("7286890544");
        employee.setTeam(createTeam(TEAM_ID_1));
        employee.setEmployeeType(createEmployeeType(1L));
        employee.setJoiningDate(new Date());
        employee.setEmployeeId("BT149");
        employee.setManagerId(1L);
        employee.setPassword("1234");
        employee.setFirstTimeUser(true);
        employee.setOtp("2244");
        employee.setOtpGenerationTime(2L);
        employee.setIsAdmin(true);
        employee.setIsActive(true);
        employee.setCreatedBy("Pooja");
        employee.setUpdatedBy("Pooja");
        employee.setCreatedDate(new Date());
        employee.setUpdatedDate(new Date());
        return employee;
    }

    public static EmployeeDTO createNomineeEmployeeDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFirstName("pooja");
        employeeDTO.setLastName("oggu");
        employeeDTO.setEmail("pooja@gmail.com");
        employeeDTO.setPhoneNumber("7286890544");
        employeeDTO.setTeam(updatingTeamDTOData(TEAM_ID_1));
        employeeDTO.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDTO.setJoiningDate(new Date());
        employeeDTO.setEmployeeId("BT149");
        employeeDTO.setManagerId(1L);
        employeeDTO.setPassword("1234");
        employeeDTO.setFirstTimeUser(true);
        employeeDTO.setOtp("2244");
        employeeDTO.setOtpGenerationTime(2L);
        employeeDTO.setIsAdmin(true);
        employeeDTO.setIsActive(true);
        employeeDTO.setCreatedBy("Pooja");
        employeeDTO.setUpdatedBy("Pooja");
        employeeDTO.setCreatedDate(new Date());
        employeeDTO.setUpdatedDate(new Date());
        return employeeDTO;
    }

    public  static EmployeeDTO createNominatedByEmployeeDTO(){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(1L);
        employeeDTO.setFirstName("jani");
        employeeDTO.setLastName("shaik");
        employeeDTO.setEmail("jani@gmail.com");
        employeeDTO.setPhoneNumber("9286890544");
        employeeDTO.setTeam(updatingTeamDTOData(TEAM_ID_1));
        employeeDTO.setEmployeeType(createEmployeeTypeDTO(1L));
        employeeDTO.setJoiningDate(new Date());
        employeeDTO.setEmployeeId("BT100");
        employeeDTO.setManagerId(3L);
        employeeDTO.setPassword("123");
        employeeDTO.setFirstTimeUser(true);
        employeeDTO.setOtp("224");
        employeeDTO.setOtpGenerationTime(4L);
        employeeDTO.setIsAdmin(true);
        employeeDTO.setIsActive(true);
        employeeDTO.setCreatedBy("Pooja");
        employeeDTO.setUpdatedBy("Pooja");
        employeeDTO.setCreatedDate(new Date());
        employeeDTO.setUpdatedDate(new Date());
        return employeeDTO;
    }
}
