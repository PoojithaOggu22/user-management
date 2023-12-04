package com.bilvantis.user.api.service.impl;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.service.EmployeeService;
import com.bilvantis.user.api.service.LoginService;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.dao.data.model.Employee;
import com.bilvantis.user.dao.data.repository.EmployeeRepository;
import com.bilvantis.user.data.model.EmployeeDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.bilvantis.user.api.util.EmployeeSupport.convertEmployeeEntityToEmployeeDto;
import static com.bilvantis.user.api.util.EmployeeSupport.decodingPassword;

@Service("loginServiceImpl")
@Slf4j
public class LoginServiceImpl implements LoginService<EmployeeDTO, Long> {

    @Autowired
    private UserProperties userProperties;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private JwtTokenService jwtTokenService;
    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeService<EmployeeDTO, Long> employeeService;
    @Override
    public EmployeeDTO verifyEmployeeLogin(String empId, String employeePassword) {

        try {
            Optional<Employee> optionalEmployee = employeeRepository.findByEmployeeId(empId);
            if (optionalEmployee.isPresent()) {
                Employee employeeEntity = optionalEmployee.get();
                //Decoding the Password
                String decodedPassword = decodingPassword(employeeEntity.getPassword());
                //Comparing the dbDecodedPassword with the given login password
                if (StringUtils.equals(decodedPassword, employeePassword)) {
                   return convertEmployeeEntityToEmployeeDto(employeeEntity);
                }
                throw new ApplicationException(userProperties.getEmployeeUserIdOrPasswordMismatch());
            }
            throw new ApplicationException(userProperties.getEmployeesNotAvailable());
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(e.getMessage());
        }
    }
}

