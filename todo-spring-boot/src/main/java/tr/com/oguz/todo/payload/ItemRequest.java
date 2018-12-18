package tr.com.oguz.todo.payload;

import java.util.Date;
import java.util.List;

public class ItemRequest {

	private String name;
	private String description;
	private Date deadline;
	private Long itemList;
	private List<Long> dependentItems;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Long getItemList() {
		return itemList;
	}

	public void setItemList(Long itemList) {
		this.itemList = itemList;
	}

	public List<Long> getDependentItems() {
		return dependentItems;
	}

	public void setDependentItems(List<Long> dependentItems) {
		this.dependentItems = dependentItems;
	}

}
