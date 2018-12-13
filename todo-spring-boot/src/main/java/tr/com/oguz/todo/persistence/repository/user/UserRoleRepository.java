package tr.com.oguz.todo.persistence.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tr.com.oguz.todo.persistence.entity.user.UserRoleEnum;
import tr.com.oguz.todo.persistence.entity.user.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
	public Optional<UserRole> findByName(UserRoleEnum roleUser);
}