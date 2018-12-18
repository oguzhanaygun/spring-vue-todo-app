package tr.com.oguz.todo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.oguz.todo.payload.ApiResponse;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends AppException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException() {
		super(new ApiResponse(false, "hop dedik!!", null));

	}

	public AuthenticationException(ApiResponse apiResponse) {
		super(apiResponse);
	}

}
