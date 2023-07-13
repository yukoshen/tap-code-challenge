package com.kjslocal.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.kjslocal.api.TapCodeChallengeController;
import com.kjslocal.dto.ErrorDto;
import com.kjslocal.dto.ErrorMessage;

import lombok.extern.slf4j.Slf4j;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice(assignableTypes = TapCodeChallengeController.class)
@Slf4j
public class TapCodeChallengeExceptionAdvice {

	private final String LOG_TEMPLATE = "{}::{}() - {}";

	@ExceptionHandler(TapCodeChallengeException.class)
	public ResponseEntity<ErrorDto> handleUserException(TapCodeChallengeException ex) {

		ErrorDto error = new ErrorDto();
		List<ErrorMessage> errors = new ArrayList<>();
		
		ErrorMessage errMsg = ErrorMessage.builder().fieldName("exception")
				.message(ex.getErrMessage()).build();
		
		errors.add(errMsg);
		
		error = ErrorDto.builder().errors(errors).build();

		log.error(LOG_TEMPLATE, getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),
				ex.getMessage());

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		ErrorDto error = new ErrorDto();
		List<ErrorMessage> errors = new ArrayList<>();

		if (ex.getBindingResult().hasErrors()) {
			for (final FieldError field : ex.getBindingResult().getFieldErrors()) {
				ErrorMessage errMsg = ErrorMessage.builder().fieldName(field.getField())
						.message(field.getDefaultMessage()).build();
				errors.add(errMsg);
			}
		}

		error = ErrorDto.builder().errors(errors).build();

		log.error(LOG_TEMPLATE, getClass().getSimpleName(), Thread.currentThread().getStackTrace()[1].getMethodName(),
				ex.getMessage());

		return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

}
