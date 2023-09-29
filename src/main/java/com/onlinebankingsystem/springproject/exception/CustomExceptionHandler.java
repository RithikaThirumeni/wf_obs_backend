package com.onlinebankingsystem.springproject.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		Map<String, Object> responseBody = new LinkedHashMap<>();
		responseBody.put("timestamp", new Date());
		responseBody.put("status", status.value());

		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		responseBody.put("errors", errors);

		return new ResponseEntity<>(responseBody, headers, status);
	}

	@ExceptionHandler(value = Exception.class)
	public @ResponseBody ResponseEntity<Object> handleResoureNotFoundException(Exception ex) {
		
		HashMap<String,Object> result = new HashMap<>();
		String responseText=ex.toString();
		
		
		result.put("responseText", responseText);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@ExceptionHandler(value = AdminNotFoundException.class)
	public @ResponseBody ResponseEntity<Object> handleAdminNotFoundException(Exception ex) {
		
		HashMap<String,Object> result = new HashMap<>();
		result.put("responseText", "Admin Not Found");
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = AccountNotFoundException.class)
	public @ResponseBody ResponseEntity<Object> handleAccountNotFoundException(Exception ex) {
		
		HashMap<String,Object> result = new HashMap<>();
		result.put("responseText", "Account Not Found");
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value = CustomerNotFoundException.class)
	public @ResponseBody ResponseEntity<Object> handleCustomerNotFoundException(Exception ex) {
		
		HashMap<String,Object> result = new HashMap<>();
		result.put("responseText", "Customer Not Found");
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}
	@ExceptionHandler(value=ValidationException.class)
	public @ResponseBody ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		
		HashMap<String,Object> result = new HashMap<>();
		String responseText=ex.getMessage();
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		result.put("responseText", errors+", "+responseText);
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value=ClassCastException.class)
	public @ResponseBody ResponseEntity<Object> handleClassCastException(ClassCastException ex){
		HashMap<String,Object> result = new HashMap<>();
		String responseText=ex.getMessage();
		

		result.put("responseText", "Class Cast Exception was raised in server side");
		return new ResponseEntity<>(result, HttpStatus.NOT_FOUND);	
		
	}
}
