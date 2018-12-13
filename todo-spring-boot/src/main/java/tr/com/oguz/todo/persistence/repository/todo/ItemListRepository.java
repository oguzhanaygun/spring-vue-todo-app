package tr.com.oguz.todo.persistence.repository.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.oguz.todo.persistence.entity.todo.ItemList;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Long> {
	@Query("SELECT list from ItemList list where list.createdBy.username = ?1")
	public List<ItemList> findByUsername(String username);

}
