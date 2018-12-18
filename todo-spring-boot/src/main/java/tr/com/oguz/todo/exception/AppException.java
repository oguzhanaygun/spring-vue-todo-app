package tr.com.oguz.todo.exception;

import tr.com.oguz.todo.payload.ApiResponse;

public class AppException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ApiResponse response;

	public AppException(ApiResponse response) {
		super(response.getMessage());
		this.response = response;
	}

	public AppException(String message) {
		super(message);
	}

	public AppException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiResponse getResponse() {
		return response;
	}

	public void setResponse(ApiResponse response) {
		this.response = response;
	}

}
