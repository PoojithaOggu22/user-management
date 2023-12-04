package com.bilvantis.user.data.model;

import com.bilvantis.user.data.util.OnCreate;
import com.bilvantis.user.data.util.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

import static com.bilvantis.user.data.util.UserDataModelConstants.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO extends BaseDTO implements Serializable {

    @Null(groups = OnCreate.class, message = MESSAGE_ID_ON_CREATE)
    @NotNull(groups = OnUpdate.class, message = MESSAGE_ID_ON_UPDATE)
    private Long id;

    @NotBlank(message = MESSAGE_FIRST_NAME)
    private String firstName;

    private String lastName;

    //TODO: Add email validation
    @NotBlank(message = MESSAGE_EMAIL)
    private String email;

    //TODO: Add phone number validation
    @NotBlank(message = MESSAGE_PHONE_NUMBER)
    private String phoneNumber;

    @NotNull(message = MESSAGE_TEAM)
    private TeamDTO team;

    @NotNull(message = MESSAGE_EMPLOYEE_TYPE)
    private EmployeeTypeDTO employeeType;

    @NotNull(message = MESSAGE_JOINING_DATE)
    private Date joiningDate;

    @NotBlank(message = MESSAGE_EMPLOYEE_ID)
    private String employeeId;

    @NotNull(message = MESSAGE_MANAGER_ID)
    private Long managerId;

    @NotBlank(message = MESSAGE_PASSWORD)
    private String password;

    private Boolean firstTimeUser;

    private String otp;

    private Long otpGenerationTime;

    private Boolean isAdmin;
}
