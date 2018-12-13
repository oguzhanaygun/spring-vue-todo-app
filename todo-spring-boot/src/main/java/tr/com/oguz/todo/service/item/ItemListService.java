package tr.com.oguz.todo.service.item;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import tr.com.oguz.todo.exception.BadRequestException;
import tr.com.oguz.todo.persistence.entity.todo.ItemList;
import tr.com.oguz.todo.persistence.repository.todo.ItemListRepository;
import tr.com.oguz.todo.service.BaseService;

@Service
public class ItemListService extends BaseService<ItemListRepository, ItemList> {

	@PreAuthorize("#userName == principal.getUsername() or hasRole('ROLE_ADMIN')")
	public List<ItemList> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public ItemList save(ItemList list) {

		if (list.getCreatedBy().getId() == null) {
			throw new BadRequestException("createdBy id is required");
		}

		return repository.save(list);
	}
}
