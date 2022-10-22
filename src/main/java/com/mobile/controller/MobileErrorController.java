package com.mobile.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mobile.dto.ErrorResponseDto;
import com.mobile.exception.MobileAlreadyExistsException;
import com.mobile.exception.MobileDoesNotExistsException;
import com.mobile.exception.MobilePriceExceedException;
import com.mobile.exception.PriceRangeException;

@RestControllerAdvice
public class MobileErrorController {

	HttpStatus status;
	
	@ExceptionHandler(MobileAlreadyExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleMobileAlreadyExistsException(Exception ex, HttpServletRequest request){
		status = HttpStatus.CONFLICT;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
	}
	
	@ExceptionHandler(MobileDoesNotExistsException.class)
	public ResponseEntity<ErrorResponseDto> handleMobileDoesNotExistsException(Exception ex, HttpServletRequest request){
		status = HttpStatus.NOT_FOUND;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
	}
	
	@ExceptionHandler(MobilePriceExceedException.class)
	public ResponseEntity<ErrorResponseDto> handleMobilePriceExceedException(Exception ex, HttpServletRequest request){
		status = HttpStatus.BAD_REQUEST;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
	}
	
	@ExceptionHandler(PriceRangeException.class)
	public ResponseEntity<ErrorResponseDto> handlePriceRangeException(Exception ex, HttpServletRequest request){
		status = HttpStatus.BAD_REQUEST;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDto> handleOtherExceptions(Exception ex, HttpServletRequest request){
		status = HttpStatus.INTERNAL_SERVER_ERROR;
		ErrorResponseDto errorObj = new ErrorResponseDto(LocalDateTime.now() ,
												status.value(), 
												status.getReasonPhrase(), 
												ex.getMessage(), 
												request.getRequestURI()
												);
		return ResponseEntity.status(status).body(errorObj);
		
		
	}
}
