package tr.com.oguz.todo.persistence.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.oguz.todo.persistence.entity.todo.Status;
import tr.com.oguz.todo.persistence.entity.todo.StatusName;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
	public Status findByName(StatusName name);
}
