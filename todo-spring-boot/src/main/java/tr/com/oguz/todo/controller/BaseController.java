package tr.com.oguz.todo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tr.com.oguz.todo.exception.CustomResponseEntityExceptionHandler;
import tr.com.oguz.todo.security.CurrentUser;
import tr.com.oguz.todo.security.UserPrincipal;
import tr.com.oguz.todo.service.BaseService;

public abstract class BaseController<S extends BaseService> extends CustomResponseEntityExceptionHandler {

	@Autowired
	protected S service;

	@GetMapping("/")
	public ResponseEntity<?> getAll(@CurrentUser UserPrincipal currentUser) {

		return ResponseEntity.ok(service.getAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getOne(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		return ResponseEntity.ok(service.get(id));
	}

	@GetMapping("/{id}/exist")
	public ResponseEntity<?> exist(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		boolean exist = service.Exist(id);
		return exist ? ResponseEntity.ok(exist) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@CurrentUser UserPrincipal currentUser, @PathVariable("id") long id) {
		service.delete(id);
		return ResponseEntity.accepted().build();
	}
}
