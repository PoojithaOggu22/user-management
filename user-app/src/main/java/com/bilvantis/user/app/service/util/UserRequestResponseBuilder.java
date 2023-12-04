package com.bilvantis.user.app.service.util;

import com.bilvantis.user.api.exception.ErrorResponse;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UserRequestResponseBuilder {

    public static UserResponseDTO buildResponseDTO(Object body,String status,List<ErrorResponse> errors){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setBody(body);
        userResponseDTO.setStatus(status);
        userResponseDTO.setErrors(errors);
        return userResponseDTO;
    }

}
