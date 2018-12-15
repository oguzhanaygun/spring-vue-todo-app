package tr.com.oguz.todo.payload;


public class ApiResponse<T> {
    private Boolean success;
    private String message;
    private T data;

    public ApiResponse(Boolean success, String message,T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
    
}
