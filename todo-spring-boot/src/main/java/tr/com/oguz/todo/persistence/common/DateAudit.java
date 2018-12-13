package tr.com.oguz.todo.persistence.common;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Embeddable
public class DateAudit implements Serializable {

	private static final long serialVersionUID = 1L;

	public DateAudit() {
		this.createdAt = new Date();
		this.updatedAt = new Date();
	}

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@LastModifiedDate
	@Column(nullable = false)
	private Date updatedAt;

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}