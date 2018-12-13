package tr.com.oguz.todo.persistence.repository.todo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.oguz.todo.persistence.entity.todo.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	
	@Query("SELECT i FROM Item i LEFT JOIN FETCH i.dependentItems where i.id = ?1")
	public Item getWithDependentItemsById(Long id);

}
