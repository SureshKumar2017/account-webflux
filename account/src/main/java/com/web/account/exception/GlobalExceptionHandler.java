package com.web.account.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.web.account.controller.AccountController;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);

	
	@ExceptionHandler({ ValidationException.class }) //Validation Exception Handle
	public ResponseEntity<APIError> validationException(ValidationException ex, HttpServletRequest request) {

		LOGGER.error("validation exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(APIError.builder().errorMessage(ex.getLocalizedMessage())
				.errorCode(HttpStatus.BAD_REQUEST.toString()).request(request.getRequestURI())
				.requestType(request.getMethod()).customMessage("Request is not valid").build(),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class }) //Common Exception Handle
	public ResponseEntity<APIError> genericException(Exception ex, HttpServletRequest request) {

		LOGGER.error("exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(
				APIError.builder().errorMessage(ex.getLocalizedMessage())
						.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString()).request(request.getRequestURI())
						.requestType(request.getMethod()).customMessage("Could not process request").build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({ AccountException.class }) //Service Exception Handle
	public ResponseEntity<APIError> serviceException(Exception ex, HttpServletRequest request) {

		LOGGER.error("exception : " + ex.getLocalizedMessage() + " for " + request.getRequestURI());

		return new ResponseEntity<>(
				APIError.builder().errorMessage(ex.getLocalizedMessage())
						.errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString()).request(request.getRequestURI())
						.requestType(request.getMethod()).customMessage("Could not process request").build(),
				HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
