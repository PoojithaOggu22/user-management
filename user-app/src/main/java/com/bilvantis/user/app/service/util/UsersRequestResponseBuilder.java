package com.bilvantis.user.app.service.util;

import com.bilvantis.user.api.exception.ErrorResponse;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import org.springframework.util.ObjectUtils;

import java.util.List;

import com.bilvantis.user.api.exception.ErrorResponse;
import com.bilvantis.user.app.service.model.UserResponseDTO;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class UsersRequestResponseBuilder {

    public static UserResponseDTO buildResponseDTO(Object body, Integer pageNumber, Integer size,
                                                   Integer totalPages, List<ErrorResponse> errors,
                                                   String status) {

        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setBody(body);
        responseDTO.setStatus(status);
        responseDTO.setErrors(errors);
        responseDTO.setPageNo(pageNumber);
        responseDTO.setPerPage(size);
        responseDTO.setTotalPages(totalPages);
        return responseDTO;
    }

    public static ErrorResponse buildErrorDTO(String errorMessage, String fieldId) {
        return new ErrorResponse((ObjectUtils.isEmpty(errorMessage) ? UserAppConstant.NA : errorMessage),
                (ObjectUtils.isEmpty(fieldId) ? UserAppConstant.NA : fieldId));
    }
}

