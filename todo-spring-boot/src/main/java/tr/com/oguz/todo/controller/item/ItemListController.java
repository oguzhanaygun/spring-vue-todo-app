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
import tr.com.oguz.todo.persistence.entity.todo.ItemList;
import tr.com.oguz.todo.service.item.ItemListService;

@RestController
@RequestMapping("/api/todo/itemList")
public class ItemListController extends BaseController<ItemListService> {

	@GetMapping("/getByUserName/{username}")
	public ResponseEntity<?> getOne(@PathVariable("username") String username) {
		return ResponseEntity.ok(service.findByUsername(username));
	}

	@PostMapping()
	public ResponseEntity<?> create(@Valid @RequestBody ItemList itemlist) {
		itemlist = service.save(itemlist);
		if (itemlist == null) {
			throw new BadRequestException("ups! something went wrong.");
		}
		return ResponseEntity.ok(itemlist);
	}
}
