//package com.financialhouseproject.exception;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@ControllerAdvice
//public class GlobalExceptionHandler {
//    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
//    public ResponseEntity<ErrorMessage> handleIllegalArgumentException(IllegalArgumentException exception) {
//        EErrorType EErrorType = INTERNAL_ERROR;
//        return new ResponseEntity<>(createError(EErrorType, exception), HttpStatus.BAD_REQUEST);
//    }
//}
