package com.springboot.udemy.Exception;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @Value(value = "${data.exception.message1}")
    private String message1;
    @Value(value = "${data.exception.message2}")
    private String message2;
    @Value(value = "${data.exception.message3}")
    private String message3;
    
    @ExceptionHandler(value = CustomerAlreadyExists.class )
	public ResponseEntity<String> handleCustomerAlreadyFoundException(CustomerAlreadyExists customerAlreadyExists)
	{
		
		return new ResponseEntity<String>(message1,HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(value = CustomerNotFound.class )
	public ResponseEntity<String> handleCustomerNotFoundException(CustomerNotFound customerNotFound)
	{
		
		return new ResponseEntity<String>(message2,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(value = RuntimeException.class )
	public ResponseEntity<String> handleRuntimeException(RuntimeException runtimeException)
	{
		
		return new ResponseEntity<String>(message3,HttpStatus.NOT_FOUND);
	}
}