package com.intuit.stockservice.exceptionhandlers;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value= {Exception.class})
	public ResponseEntity<Object> handleAllAppException(Exception ex, WebRequest request) {
		String localizedErrMsg = ex.getLocalizedMessage()==null ?
				ex.toString() : ex.getLocalizedMessage();
		
		ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), localizedErrMsg);
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(value= {CustomRuntimeException.class})
	public ResponseEntity<Object> handleCustomException(CustomRuntimeException ex, WebRequest request) {
		String localizedErrMsg = ex.getLocalizedMessage()==null ?
				ex.toString() : ex.getLocalizedMessage();
		
		ErrorMessageDto errorMessage = new ErrorMessageDto(new Date(), localizedErrMsg);
		
		return new ResponseEntity<>(
				errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
