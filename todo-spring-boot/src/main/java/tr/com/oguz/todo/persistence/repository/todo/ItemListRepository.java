package tr.com.oguz.todo.persistence.repository.todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tr.com.oguz.todo.persistence.entity.todo.ItemList;

@Repository
public interface ItemListRepository extends JpaRepository<ItemList, Long> {
	@Query("select distinct list from ItemList list left join fetch list.items where list.createdBy.username = ?1 order by list.id")
	public List<ItemList> findByUsername(String username);
	
	@Query("select distinct list from ItemList list left join fetch list.items where list.createdBy.username = ?1 order by list.id")
	public List<ItemList> findById(String username);

}
