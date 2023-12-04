package com.bilvantis.user.app.service.model;

import com.bilvantis.user.api.exception.ErrorResponse;
import com.bilvantis.user.data.model.PageDetailsDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserResponseDTO extends PageDetailsDTO {

    private Object body;
    private String status;
    private List<ErrorResponse> errors;

}
