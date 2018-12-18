package tr.com.oguz.todo.persistence.entity.todo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "STATUS")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id")
public class Status implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true, updatable = false, nullable = false, unique = true)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 60)
	private StatusName name;

	@Column
	private String description;

	@Column
	private Boolean isDone;

	@OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
	private Set<Item> items;

	public Status() {
	}

	public Status(StatusName name, String description, boolean isDone) {
		this.name = name;
		this.description = description;
		this.isDone = isDone;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusName getName() {
		return name;
	}

	public void setName(StatusName name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@JsonIgnore
	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public Boolean getIsDone() {
		return isDone;
	}

	public void setIsDone(Boolean isDone) {
		this.isDone = isDone;
	}

}
