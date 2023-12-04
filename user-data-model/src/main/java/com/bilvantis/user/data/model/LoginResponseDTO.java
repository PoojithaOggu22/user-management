package com.bilvantis.user.data.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private EmployeeDTO employeeDetails;

    private Boolean monthFlag;

}
