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
import tr.com.oguz.todo.exception.BadRequestException;
import tr.com.oguz.todo.persistence.entity.todo.Item;
import tr.com.oguz.todo.service.item.ItemService;

@RestController
@RequestMapping("/api/todo/item")
public class ItemController extends BaseController<ItemService> {

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@PathVariable("id") long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@PostMapping("/{id}/done")
	public ResponseEntity<?> doneTodo(@PathVariable("id") long id) {
		return ResponseEntity.ok(service.doneItem(id));
	}

	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody Item item) {
		item = service.save(item);
		if (item == null) {
			throw new BadRequestException("ups! something went wrong.");
		}
		return ResponseEntity.ok(item);
	}

	@PostMapping("/{listId}")
	public ResponseEntity<?> create(@PathVariable("listId") long listId, @Valid @RequestBody Item item) {
		item = service.saveToList(listId, item);
		if (item == null) {
			throw new BadRequestException("ups! something went wrong.");
		}
		return ResponseEntity.ok(item);
	}

}
