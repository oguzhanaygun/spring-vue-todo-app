package tr.com.oguz.todo.persistence.entity.todo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import tr.com.oguz.todo.persistence.common.DateAudit;

@Table(name = "TODO_ITEM")
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Item implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(insertable = true, updatable = false, nullable = false, unique = true)
	private Long id;

	@Column()
	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;

	@Column()
	private String descripition;

	@Column()
	private Date deadLine;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "status_id", nullable = false, updatable = true, insertable = true)
	private Status status;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "list_id", nullable = false, insertable = true, updatable = false)
	@Valid
	@JsonBackReference
	private ItemList list;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ITEM_DEPENDENCY_MAP", joinColumns = @JoinColumn(name = "todo_id"), inverseJoinColumns = @JoinColumn(name = "dependent_id"))
	@JsonManagedReference
	private List<Item> dependentItems;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "ITEM_DEPENDENCY_MAP", joinColumns = @JoinColumn(name = "dependent_id"), inverseJoinColumns = @JoinColumn(name = "todo_id"))
	@JsonManagedReference
	private List<Item> dependentByItems;

	@Embedded
	DateAudit dateAudit;

	public Item() {
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

	public String getDescripition() {
		return descripition;
	}

	public void setDescripition(String descripition) {
		this.descripition = descripition;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Item> getDependentItems() {
		return dependentItems;
	}

	public void setDependentItems(List<Item> dependentItems) {
		this.dependentItems = dependentItems;
	}

	public List<Item> getDependentByItems() {
		return dependentByItems;
	}

	public void setDependentByItems(List<Item> dependentByItems) {
		this.dependentByItems = dependentByItems;
	}

	public DateAudit getDateAudit() {
		return dateAudit;
	}

	public void setDateAudit(DateAudit dateAudit) {
		this.dateAudit = dateAudit;
	}

	public ItemList getList() {
		return list;
	}

	public void setList(ItemList list) {
		this.list = list;
	}

}
