package com.bilvantis.user.app.service.controller;

import com.bilvantis.user.api.service.EmployeeService;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import com.bilvantis.user.app.service.util.UserAppConstant;
import com.bilvantis.user.app.service.util.UserRequestResponseBuilder;
import com.bilvantis.user.app.service.util.UsersRequestResponseBuilder;
import com.bilvantis.user.data.model.EmployeeDTO;
import com.bilvantis.user.data.util.OnCreate;
import com.bilvantis.user.data.util.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping(value = {"/employee", "/noAuth/employee"})
public class EmployeeController {

    @Autowired
    @Qualifier("employeeServiceImpl")
    private EmployeeService<EmployeeDTO, Long> employeeService;


    @GetMapping
    public ResponseEntity<UserResponseDTO> getAllEmployees(@RequestParam(required = false) Integer pageNumber,
                                                           @RequestParam(required = false) Integer size,
                                                           @RequestParam(required = false) String searchTerm) {
        Page<EmployeeDTO> employees = employeeService.getAllEmployees(pageNumber, size, searchTerm);
        return new ResponseEntity<>(UsersRequestResponseBuilder.buildResponseDTO(
                employees.getContent(), employees.getPageable().getPageNumber(), employees.getPageable().getPageSize(),
                employees.getTotalPages(), null, UserAppConstant.SUCCESS),
                HttpStatus.OK);
    }
    @GetMapping("/managerReportees/{id}")
    public ResponseEntity<UserResponseDTO> getManagersReportees(@NotNull @PathVariable Long id) {
        return new ResponseEntity<>(UsersRequestResponseBuilder.buildResponseDTO(
                employeeService.getManagersDirectAndIndirectReportees(id), null, null, null, null, UserAppConstant.SUCCESS),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getEmployee(@NotNull @PathVariable Long id) {
        return new ResponseEntity<>(UsersRequestResponseBuilder.buildResponseDTO(
                employeeService.getEmployeeById(id), null, null, null, null, UserAppConstant.SUCCESS),
                HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<UserResponseDTO> createEmployee(@NotNull @Valid @RequestBody EmployeeDTO employeeDTO) {
        return new ResponseEntity<>(UsersRequestResponseBuilder.buildResponseDTO(
                employeeService.createEmployee(employeeDTO), null, null, null, null, UserAppConstant.SUCCESS),
                HttpStatus.CREATED);
    }

    @Validated(OnUpdate.class)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateEmployee(
            @NotNull @PathVariable Long id, @NotNull @Valid @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO updateEmployee = employeeService.updateEmployeeById(id, employeeDTO);
        return new ResponseEntity<>(UsersRequestResponseBuilder.buildResponseDTO(updateEmployee, null, null, null,
                null, UserAppConstant.SUCCESS), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@NotNull @PathVariable Long id) {
        employeeService.deleteEmployeeById(id);
    }



}
