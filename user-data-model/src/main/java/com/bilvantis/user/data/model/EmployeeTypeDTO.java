package com.bilvantis.user.data.model;

import com.bilvantis.user.data.util.OnCreate;
import com.bilvantis.user.data.util.OnUpdate;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import static com.bilvantis.user.data.util.UserDataModelConstants.MESSAGE_ID_ON_CREATE;
import static com.bilvantis.user.data.util.UserDataModelConstants.MESSAGE_ID_ON_UPDATE;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmployeeTypeDTO extends BaseDTO implements Serializable {

    @Null(groups = OnCreate.class, message = MESSAGE_ID_ON_CREATE)
    @NotNull(groups = OnUpdate.class, message = MESSAGE_ID_ON_UPDATE)
    private Long id;
    private String designation;
}
