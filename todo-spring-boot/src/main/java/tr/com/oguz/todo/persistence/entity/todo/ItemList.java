package tr.com.oguz.todo.persistence.entity.todo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import tr.com.oguz.todo.persistence.common.DateAudit;
import tr.com.oguz.todo.persistence.entity.user.User;

@Entity
@Table(name = "TODO_LIST")
@JsonIdentityInfo(generator = ObjectIdGenerators.None.class, property = "id")
public class ItemList implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true, updatable = false, nullable = false, unique = true)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "list", orphanRemoval = true, fetch = FetchType.EAGER)
	@OrderBy("id ASC")
	@JsonManagedReference
	private Set<Item> items;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "createdBy", nullable = false, updatable = false, insertable = true)
	@JsonBackReference
	private User createdBy;

	@Embedded
	DateAudit dateAudit;

	public ItemList() {
		this.dateAudit = new DateAudit();
	}

	public ItemList(Long id, String name, Set<Item> items, User created) {
		this.id = id;
		this.name = name;
		this.items = items;
		this.createdBy = created;
		this.dateAudit = new DateAudit();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Item> getItems() {
		return items;
	}

	public void setItems(Set<Item> items) {
		this.items = items;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User cteatedBy) {
		this.createdBy = cteatedBy;
	}

	public DateAudit getDateAudit() {
		return dateAudit;
	}

	public void setDateAudit(DateAudit dateAudit) {
		this.dateAudit = dateAudit;
	}

}
