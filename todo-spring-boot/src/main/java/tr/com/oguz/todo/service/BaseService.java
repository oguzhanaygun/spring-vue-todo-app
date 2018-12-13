package tr.com.oguz.todo.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.access.prepost.PreAuthorize;

import tr.com.oguz.todo.exception.NotFoundException;

public abstract class BaseService<T extends JpaRepository<E, Long>, E> {

	@Autowired
	protected T repository;

	public E save(E entity) {
		return repository.save(entity);
	}

	public E get(Long id) {
		E entity = repository.findById(id).orElseThrow(
				() -> new NotFoundException("no data found with value:" + id));
		return entity;
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Iterable<E> getAll() {
		return repository.findAll();
	}

	public boolean Exist(Long id) {
		return repository.existsById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(Long id) {
		repository.deleteById(id);
	}

	public Iterable<E> save(Iterable<E> list) {
		return repository.saveAll(list);
	}

	@SuppressWarnings("unchecked")
	public Iterable<E> save(E... list) {
		return repository.saveAll(Arrays.asList(list));
	}
}
