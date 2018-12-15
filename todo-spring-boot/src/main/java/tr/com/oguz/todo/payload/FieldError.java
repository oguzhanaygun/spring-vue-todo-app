package tr.com.oguz.todo.payload;

public class FieldError {

	private String fieldName;
	private String message;
	private Object value;

	public FieldError() {

	}

	public FieldError(String parameterName, String message, Object value) {
		super();
		this.fieldName = parameterName;
		this.message = message;
		this.value = value;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
