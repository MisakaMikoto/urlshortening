package com.kakaopay.url.config.exception.hanlder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

    private final MessageSourceAccessor messageSourceAccessor;

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception) {

        log.error("exception : {} ", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(messageSourceAccessor.getMessage("err.internal_server_error"));
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> methodArgumentNotValidException(
            MethodArgumentNotValidException methodArgumentNotValidException) {

        log.error("exception : {} ", methodArgumentNotValidException.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(messageSourceAccessor.getMessage("err.bad_request"));
    }
}
