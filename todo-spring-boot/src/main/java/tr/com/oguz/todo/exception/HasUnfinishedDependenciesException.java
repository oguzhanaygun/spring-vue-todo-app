package tr.com.oguz.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.oguz.todo.payload.ApiResponse;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HasUnfinishedDependenciesException extends AppException {

	private static final long serialVersionUID = 1L;

	public HasUnfinishedDependenciesException(ApiResponse response) {
		super(response);
	}
}
