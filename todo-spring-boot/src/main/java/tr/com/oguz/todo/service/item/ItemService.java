package tr.com.oguz.todo.service.item;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tr.com.oguz.todo.exception.BadRequestException;
import tr.com.oguz.todo.exception.HasUnfinishedDependenciesException;
import tr.com.oguz.todo.persistence.common.DateAudit;
import tr.com.oguz.todo.persistence.entity.todo.Item;
import tr.com.oguz.todo.persistence.entity.todo.ItemList;
import tr.com.oguz.todo.persistence.entity.todo.StatusName;
import tr.com.oguz.todo.persistence.repository.todo.ItemRepository;
import tr.com.oguz.todo.service.BaseService;

@Service
public class ItemService extends BaseService<ItemRepository, Item> {

	@Autowired
	private StatusService statusService;

	@Autowired
	private ItemListService itemListService;

	public Item doneItem(long id) {
		Item item = repository.getWithDependentItemsById(id);
		List<Item> unFinishedItems = item.getDependentItems().stream()
				.filter((dependentItem) -> !dependentItem.getStatus().getIsDone()).collect(Collectors.toList());
		if (unFinishedItems != null && !unFinishedItems.isEmpty()) {
			throw new HasUnfinishedDependenciesException(item, unFinishedItems);
		}
		item.setStatus(statusService.findByName(StatusName.DONE));
		return repository.save(item);
	}

	@Override
	public Item save(Item item) {
		item.setStatus(statusService.findByName(StatusName.NEW));
		item.setDateAudit(new DateAudit());
		if(item.getList() != null) {
			if(item.getList().getCreatedBy().getId() == null) {
				throw new BadRequestException("createdBy id is required");
			}
		}
		return repository.save(item);
	}

	public Item saveToList(Long listId, Item item) {
		ItemList itemList = itemListService.get(listId);
		item.setList(itemList);
		return save(item);
	}
}
