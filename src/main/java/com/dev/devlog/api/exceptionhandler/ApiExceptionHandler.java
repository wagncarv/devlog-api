package com.dev.devlog.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.dev.devlog.domain.exception.NegocioException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	private MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Problem.Campo> campos = new ArrayList<>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome = ((FieldError) error).getField();
			String mensagem = messageSource.getMessage(error,LocaleContextHolder.getLocale());//error.getDefaultMessage();
			campos.add(new Problem.Campo(nome, mensagem));
		}
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHora(LocalDateTime.now());
		problem.setTitulo("Um ou mais campos estão inválidos.Preencha-os corretamente.");
		problem.setCampos(campos);
		return handleExceptionInternal(ex, problem, headers, status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<Object> handleNegocio(NegocioException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setDataHora(LocalDateTime.now());
		problem.setTitulo(ex.getMessage());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

}
