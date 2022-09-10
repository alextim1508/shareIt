package ru.practicum.shareit.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.util.exception.*;

import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleValidationException(ValidationException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Validation error";
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleMissingHeaderException(MissingRequestHeaderException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Validation error";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Validation error";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getCause().getMessage());
        return "Illegal database state";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(Exception e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "ResourceNotFound";
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleForbiddenException(Exception e) {
        log.error("{}", e.getClass().getSimpleName());
        return "ForbiddenException";
    }

    @ExceptionHandler(ActionIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleActionIsNotAvailableException(Exception e) {
        return "handleActionIsNotAvailableException";
    }

    @ExceptionHandler(ItemIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleItemIsNotAvailableException(Exception e) {
        return "ItemIsNotAvailableException";
    }

    @ExceptionHandler(UnsupportedStatusException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUnsupportedStatusException(UnsupportedStatusException e) {
        HashMap<String, String> map = new HashMap<>();
        map.put("error", "Unknown state: " + e.unknownStatus);
        return map;
    }

    @ExceptionHandler(StatusChangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleStatusChangeException(StatusChangeException e) {
        return "StatusChangeException";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalException(Exception e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Internal error";
    }
}