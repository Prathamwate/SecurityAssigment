package com.pratham.SecurityPratice;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerClass {	
	     
	    public ExceptionHandlerClass() {
	    	super();
	    }
	    
	    @ExceptionHandler(UsernameNotFoundException.class)
		public String UsernameNotFoundException(UsernameNotFoundException e) {
			return e.getLocalizedMessage();
		}
}
