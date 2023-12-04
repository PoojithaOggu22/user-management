package com.bilvantis.user.app.service.controller.exception;

import com.bilvantis.user.api.exception.ApplicationException;
import com.bilvantis.user.api.exception.ErrorResponse;
import com.bilvantis.user.api.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Path;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.StreamSupport;

import static com.bilvantis.user.app.service.util.UserAppConstant.GLOBAL_FIELD_ID;

@ControllerAdvice
public class UserAppExceptionHandler {

    /**
     * Handle ApplicationExceptions
     *
     * @param exception ApplicationException
     * @return ErrorResponse
     */
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ErrorResponse> handleApplicationException(ApplicationException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), GLOBAL_FIELD_ID);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handle ResourceNotFoundException
     *
     * @param exception ResourceNotFoundException
     * @return ErrorResponse
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException exception){
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage(), GLOBAL_FIELD_ID);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle MethodArgumentNotValidException
     *
     * @param exception MethodArgumentNotValidException
     * @return RewardsResponseDTO
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> handleValidationExceptions(MethodArgumentNotValidException exception){
        BindingResult bindingResult = exception.getBindingResult();
        List<ErrorResponse> errors = new ArrayList<>();
        bindingResult.getFieldErrors().forEach(fieldError ->
                errors.add(new ErrorResponse(fieldError.getDefaultMessage(), fieldError.getField()))
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle ConstraintViolationException
     *
     * @param exception ConstraintViolationException
     * @return RewardsResponseDTO
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorResponse>> handleConstraintViolationException(ConstraintViolationException exception) {
        List<ErrorResponse> errors = new ArrayList<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        constraintViolations.forEach(constraintViolation -> {
            Path.Node lastNode = StreamSupport.stream(constraintViolation.getPropertyPath().spliterator(), false)
                    .reduce((first, second) -> second).orElse(null);
            errors.add(new ErrorResponse(constraintViolation.getMessage(), Objects.nonNull(lastNode) ? lastNode.getName() : null));
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
