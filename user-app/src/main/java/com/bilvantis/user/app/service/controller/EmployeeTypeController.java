package com.bilvantis.user.app.service.controller;

import com.bilvantis.user.api.service.EmployeeTypeService;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import com.bilvantis.user.app.service.util.UserAppConstant;
import com.bilvantis.user.app.service.util.UserRequestResponseBuilder;
import com.bilvantis.user.data.model.EmployeeTypeDTO;
import com.bilvantis.user.data.util.OnCreate;
import com.bilvantis.user.data.util.OnUpdate;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee-type")
@Validated
public class EmployeeTypeController {

    @Autowired
    @Qualifier("employeeTypeServiceImpl")
    private EmployeeTypeService<EmployeeTypeDTO, Long> employeeTypeService;

    @GetMapping
    public ResponseEntity<UserResponseDTO> getAllEmployeeTypes() {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                employeeTypeService.getAllEmployeeTypes(), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getEmployeeTypeById(@PathVariable @NotNull Long id) {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                employeeTypeService.getEmployeeTypeById(id), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @Validated(OnCreate.class)
    @PostMapping
    public ResponseEntity<EmployeeTypeDTO> createEmployeeType
            (@NotNull @Valid @RequestBody EmployeeTypeDTO employeeTypeDTO) {
        return new ResponseEntity<>(employeeTypeService.createEmployeeType(employeeTypeDTO), HttpStatus.CREATED);
    }

    @Validated(OnUpdate.class)
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateEmployeeType
            (@NotNull @PathVariable Long id, @NotNull @Valid @RequestBody EmployeeTypeDTO employeeTypeDTO) {
        return new ResponseEntity<>(UserRequestResponseBuilder.buildResponseDTO(
                employeeTypeService.updateEmployeeType(id, employeeTypeDTO), UserAppConstant.SUCCESS, null),
                HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployeeTypeById(@NotNull @PathVariable Long id) {
        employeeTypeService.deleteEmployeeType(id);
    }
}
