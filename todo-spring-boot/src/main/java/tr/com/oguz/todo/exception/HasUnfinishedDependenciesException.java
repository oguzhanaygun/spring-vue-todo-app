package tr.com.oguz.todo.exception;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import tr.com.oguz.todo.persistence.entity.todo.Item;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class HasUnfinishedDependenciesException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	List<Item> unFinisheds;
	Item item;

	public HasUnfinishedDependenciesException(Item item, List<Item> items) {
		super(String.format("%s has Unfinished Dependencies:%s", item.getName(), getDependencies(items)));
		this.item = item;
		this.unFinisheds = items;
	}

	public List<Item> getUnFinisheds() {
		return unFinisheds;
	}

	public Item getItem() {
		return item;
	}

	private static String getDependencies(List<Item> items) {
		return items.stream().map(i -> i.getName()).collect(Collectors.joining(","));
	}

}
