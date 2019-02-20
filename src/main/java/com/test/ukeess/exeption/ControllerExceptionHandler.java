package com.test.ukeess.exeption;

import com.test.ukeess.error.Error;
import com.test.ukeess.error.Errors;
import com.test.ukeess.utils.ErrorUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ControllerExceptionHandler {
    @ExceptionHandler(TestNotFoundExeption.class)
    @ResponseBody
    @ResponseStatus(NOT_FOUND)
    public Errors notFound(TestNotFoundExeption e) {
        return ErrorUtils.newErrorsList(new Error(e.getField(), e.getMessage()));
    }
}
