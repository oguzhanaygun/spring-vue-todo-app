package tr.com.oguz.todo.service.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import tr.com.oguz.todo.payload.ListRequest;
import tr.com.oguz.todo.persistence.entity.todo.ItemList;
import tr.com.oguz.todo.persistence.repository.todo.ItemListRepository;
import tr.com.oguz.todo.security.UserPrincipal;
import tr.com.oguz.todo.service.BaseService;
import tr.com.oguz.todo.service.user.UserService;

@Service
public class ItemListService extends BaseService<ItemListRepository, ItemList> {

	@Autowired
	private UserService userService;

	@PreAuthorize("#userName == principal.getUsername() or hasRole('ROLE_ADMIN')")
	public List<ItemList> findByUsername(String username) {
		List<ItemList> list = repository.findByUsername(username);
		return list;
	}

	@Override
	public ItemList save(ItemList list) {
		return repository.save(list);
	}

	public ItemList save(UserPrincipal currentUser, ListRequest request) {
		ItemList list = new ItemList();
		list.setName(request.getName());
		list.setCreatedBy(userService.get(currentUser.getId()));
		return repository.save(list);
	}

	public List<ItemList> getUsersList(UserPrincipal user) {
		return repository.findByUsername(user.getUsername());
	}

}
