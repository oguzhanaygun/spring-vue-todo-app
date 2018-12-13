package tr.com.oguz.todo.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tr.com.oguz.todo.controller.BaseController;
import tr.com.oguz.todo.service.user.UserService;

@RestController
@RequestMapping("/api/todo/user")
public class UserController extends BaseController<UserService> {

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@GetMapping("/getByUserName/{username}")
	public ResponseEntity<?> getOne(@PathVariable("username") String username) {
		return ResponseEntity.ok(service.get(username));
	}
}
