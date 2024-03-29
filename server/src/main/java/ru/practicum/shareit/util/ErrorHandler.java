package ru.practicum.shareit.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.shareit.util.exception.*;

import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class ErrorHandler {

    @ExceptionHandler(ConflictException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleConflictException(ConflictException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Conflict";
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleNotFoundException(NotFoundException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Not found";
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getCause().getMessage());
        return "Illegal database state";
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleForbiddenException(Exception e) {
        log.error("{}", e.getClass().getSimpleName());
        return "Forbidden";
    }

    @ExceptionHandler(ItemIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleItemIsNotAvailableException(Exception e) {
        return "Item is not available";
    }

    @ExceptionHandler(ActionIsNotAvailableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleActionIsNotAvailableException(Exception e) {
        return "Action is not available";
    }

    @ExceptionHandler(UnsupportedStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleUnsupportedStatusException(UnsupportedStateException e) {
        return Collections.singletonMap("error", "Unknown state: " + e.unknownStatus);
    }

    @ExceptionHandler(StatusChangeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleStatusChangeException(StatusChangeException e) {
        return "Status Change is not valid";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleInternalException(Exception e) {
        log.error("{} : {}", e.getClass().getSimpleName(), e.getMessage());
        return "Internal error";
    }
}
