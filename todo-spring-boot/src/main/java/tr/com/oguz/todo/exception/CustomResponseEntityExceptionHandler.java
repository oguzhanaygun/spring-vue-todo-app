package tr.com.oguz.todo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import tr.com.oguz.todo.payload.FieldError;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(HasUnfinishedDependenciesException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(HasUnfinishedDependenciesException ex,
			WebRequest request) {

		return new ResponseEntity(ex, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors().stream()
				.map(f -> new FieldError(f.getField(), f.getDefaultMessage(), f.getRejectedValue()))
				.collect(Collectors.toList());

		return new ResponseEntity(fieldErrors, HttpStatus.BAD_REQUEST);
	}
}
