package com.learnwithmohit.exceptionhandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.learnwithmohit.exception.*;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = ResourceNotFoundException.class)
	public ResponseEntity<List<String>> handleNotFoundException(final ResourceNotFoundException e) {
		logger.error("Exception stack trace", e);

		List<String> exceptions = new ArrayList<String>();
		exceptions.add(e.getMessage());

		return new ResponseEntity<List<String>>(exceptions, null, HttpStatus.NOT_FOUND);
	}
}
