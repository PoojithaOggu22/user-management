package com.bilvantis.user.api.service.impl;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.exception.ResourceNotFoundException;
import com.bilvantis.user.api.service.EmployeeService;
import com.bilvantis.user.api.util.EmployeeSupport;
import com.bilvantis.user.api.util.UserProperties;
import com.bilvantis.user.dao.data.model.Employee;
import com.bilvantis.user.dao.data.repository.EmployeeRepository;
import com.bilvantis.user.data.model.EmployeeDTO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.bilvantis.user.api.util.EmployeeSupport.*;

@Service("employeeServiceImpl")
@Slf4j
public class EmployeeServiceImpl implements EmployeeService<EmployeeDTO, Long> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserProperties userProperties;

    @Override
    public List<EmployeeDTO> getManagersDirectAndIndirectReportees(Long managerId) {
        List<EmployeeDTO> reportingEmployees = new ArrayList<>();
        getReportingEmployeesRecursive(managerId, reportingEmployees);
        return reportingEmployees;
    }

    private void getReportingEmployeesRecursive(Long managerId, List<EmployeeDTO> reportingEmployees) {
        List<Employee> directReports = employeeRepository.findByManagerId(managerId);
        for (Employee employee : directReports) {
            EmployeeDTO employeeDTO = convertEmployeeEntityToEmployeeDto(employee);
            reportingEmployees.add(employeeDTO);
            getReportingEmployeesRecursive(employee.getId(), reportingEmployees);
        }
    }

    /**
     * This method fetches all the employees with pagination
     *
     * @param pageNumber pageNumber
     * @param size       size
     * @return Page of EmployeeDTO
     */
    public Page<EmployeeDTO> getAllEmployees(Integer pageNumber, Integer size, String searchTerm) {
        try {
            // Fetch employees by page criteria
            Page<Employee> employeesPageable = findEmployeesByPageCriteria(searchTerm, pageNumber, size);
            if (CollectionUtils.isNotEmpty(employeesPageable.getContent())) {
                List<EmployeeDTO> employeeDTOList = employeesPageable.getContent().stream()
                        .map(EmployeeSupport::convertEmployeeEntityToEmployeeDto)
                        .collect(Collectors.toList());
                return new PageImpl<>(employeeDTOList, employeesPageable.getPageable(),
                        employeesPageable.getTotalElements());
            }
            throw new ResourceNotFoundException(userProperties.getEmployeesNotAvailable());
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(e);
        }
    }

    /**
     * This method is to fetch Employee by EmployeeID
     *
     * @param id EmployeeID
     * @return EmployeeDTO
     */
    public EmployeeDTO getEmployeeById(Long id) {
        try {
            if (Objects.nonNull(id)) {
                // Fetch employee by Id
                Optional<Employee> optionalEmployee = employeeRepository.findById(id);
                if (optionalEmployee.isPresent()) {
                    return convertEmployeeEntityToEmployeeDto(optionalEmployee.get());
                }
                throw new ApplicationException(String.format(userProperties.getEmployeeNotAvailableForId(), id));
            }
            throw new ApplicationException(userProperties.getEmployeeIdMandatory());
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeesNotAvailable(), e);
        }
    }

    /**
     * This method is to create an employee in the system to be eligible for awarding/awarded
     *
     * @param employeeDTO EmployeeDTO
     * @return EmployeeDTO
     */
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        try {

            // Set FirstTimeUser
            employeeDTO.setFirstTimeUser(true);
            // Encoding the password while creating New Employee
            employeeDTO.setPassword(encodingPassword(employeeDTO.getPassword()));
            // Check if employee with employeeId already exists
            if (employeeRepository.existsByEmployeeId(employeeDTO.getEmployeeId())) {
                throw new ApplicationException(String.format(userProperties.getEmployeeAlreadyExist(), employeeDTO.getEmployeeId()));
            }

            // Save employee
            Employee employee = employeeRepository.save(convertEmployeeDtoToEmployeeEntity(employeeDTO));

            // convert employee to employeeDto
            return convertEmployeeEntityToEmployeeDto(employee);

        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeeSaveFailed(), e);
        }
    }

    /**
     * This method updates an employee details from input employeeDTO matching the input ids
     *
     * @param id          Id
     * @param employeeDTO EmployeeDTO
     * @return Updated EmployeeDTO
     */
    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employeeDTO) {
        try {

            // Validate given ID in URL and payload
            if (ObjectUtils.notEqual(id, employeeDTO.getId())) {
                throw new ApplicationException(String.format(userProperties.getEmployeeIdMismatch(), id, employeeDTO.getId()));
            }

            // Fetch Employee by ID
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);

            // Check if Employee is NULL
            if (optionalEmployee.isEmpty()) {
                throw new ResourceNotFoundException(String.format(userProperties.getEmployeeNotAvailableForId(), id));
            }

            // Save updated Employee details
            Employee updateEmployee = employeeRepository.save(convertEmployeeDtoToEmployeeEntity(employeeDTO));

            // Convert EmployeeEntity to EmployeeDto
            return convertEmployeeEntityToEmployeeDto(updateEmployee);

        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(e);
        }
    }

    /**
     * This method soft delete an employee details from input employeeDTO matching the input ids
     *
     * @param id Id
     */
    public void deleteEmployeeById(Long id) {
        try {
            Optional<Employee> employeeOpt = employeeRepository.findById(id);
            if (employeeOpt.isPresent()) {
                Employee employee = employeeOpt.get();
                employee.setIsActive(false);
                employeeRepository.save(employee);
            } else {
                throw new ResourceNotFoundException(String.format(userProperties.getEmployeeNotAvailableForId(), id));
            }
        } catch (DataAccessException e) {
            log.error(String.format(userProperties.getExceptionErrorMessage(), this.getClass().getSimpleName(),
                    e.getStackTrace()[0].getMethodName()), e);
            throw new ApplicationException(userProperties.getEmployeesNotAvailable(), e);
        }
    }

    /**
     * Find employee by searchTerm, page, size
     *
     * @param searchTerm SearchTerm
     * @param page       Page
     * @param size       Size
     * @return Page of Employee
     */
    private Page<Employee> findEmployeesByPageCriteria(String searchTerm, Integer page, Integer size) {
        Page<Employee> pageEmployee = employeeRepository.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isActive"), true)));

                // Search on firstName, lastName, employeeId
                if (StringUtils.isNotBlank(searchTerm)) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(criteriaBuilder.upper(root.get("firstName")), "%" +
                                    searchTerm.toUpperCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.upper(root.get("lastName")), "%" +
                                    searchTerm.toUpperCase() + "%"),
                            criteriaBuilder.like(criteriaBuilder.upper(root.get("employeeId")), "%" +
                                    searchTerm.toUpperCase() + "%"))
                    );
                }
                // IsActive TRUE
                predicates.add(criteriaBuilder.and(criteriaBuilder.isTrue(root.get("isActive"))));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        }, userProperties.getPageRequest(page, size));
        pageEmployee.getTotalElements();        // get total elements
        pageEmployee.getTotalPages();           // get total pages
        pageEmployee.getContent();       // get List of Employee
        return pageEmployee;
    }
}
