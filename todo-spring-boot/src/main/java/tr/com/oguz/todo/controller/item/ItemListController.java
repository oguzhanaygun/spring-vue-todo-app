package tr.com.oguz.todo.controller.item;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.oguz.todo.controller.BaseController;
import tr.com.oguz.todo.exception.AuthenticationException;
import tr.com.oguz.todo.exception.BadRequestException;
import tr.com.oguz.todo.payload.ApiResponse;
import tr.com.oguz.todo.persistence.entity.todo.ItemList;
import tr.com.oguz.todo.security.CurrentUser;
import tr.com.oguz.todo.security.UserPrincipal;
import tr.com.oguz.todo.service.item.ItemListService;

@RestController
@RequestMapping("/api/todo/itemList")
public class ItemListController extends BaseController<ItemListService> {

	@GetMapping("/getByUserName/{username}")
	public ResponseEntity<?> getOne(@PathVariable("username") String username) {
		return ResponseEntity.ok(service.findByUsername(username));
	}

	@PostMapping("/")
	public ResponseEntity<?> create(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody ItemList itemlist) {
		if (itemlist.getCreatedBy().getId() != currentUser.getId()) {
			throw new AuthenticationException(
					new ApiResponse<>(false, "You are not authorized to create list for somebody", null));
		}

		try {
			itemlist = service.save(itemlist);
		} catch (Exception e) {
			throw new BadRequestException("ups! something went wrong while saving data.");
		}

		return ResponseEntity.ok(itemlist);
	}

	@Override
	public ResponseEntity<?> getAll(@CurrentUser UserPrincipal currentUser) {
		List<ItemList> lists = service.getUsersList(currentUser);
		ApiResponse<List<ItemList>> response = new ApiResponse<List<ItemList>>(true, "ok", lists);
		return ResponseEntity.ok(response);
	}
}
