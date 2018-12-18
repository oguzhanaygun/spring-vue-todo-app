package tr.com.oguz.todo.controller.item;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.oguz.todo.controller.BaseController;
import tr.com.oguz.todo.payload.ItemRequest;
import tr.com.oguz.todo.persistence.entity.todo.Item;
import tr.com.oguz.todo.security.CurrentUser;
import tr.com.oguz.todo.security.UserPrincipal;
import tr.com.oguz.todo.service.item.ItemService;

@RestController
@RequestMapping("/api/todo/item")
public class ItemController extends BaseController<ItemService> {

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@PostMapping("/{id}/done")
	public ResponseEntity<?> doneTodo(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		return ResponseEntity.ok(service.doneItem(id));
	}

	@PostMapping("/{id}/start")
	public ResponseEntity<?> startTodo(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		return ResponseEntity.ok(service.startItem(id));
	}

	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Item item) {
		item = service.save(item);
		return ResponseEntity.ok(item);
	}

	@PostMapping("/{listId}")
	public ResponseEntity<?> create(@CurrentUser UserPrincipal currentUser, @PathVariable("listId") long listId,
			@Valid @RequestBody ItemRequest itemRequest) {
		return ResponseEntity.ok(service.saveToList(listId, itemRequest));
	}

}
