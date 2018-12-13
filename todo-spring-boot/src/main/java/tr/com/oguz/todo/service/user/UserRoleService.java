package tr.com.oguz.todo.service.user;

import org.springframework.stereotype.Service;

import tr.com.oguz.todo.exception.AppException;
import tr.com.oguz.todo.persistence.entity.user.UserRole;
import tr.com.oguz.todo.persistence.entity.user.UserRoleEnum;
import tr.com.oguz.todo.persistence.repository.user.UserRoleRepository;
import tr.com.oguz.todo.service.BaseService;

@Service
public class UserRoleService extends BaseService<UserRoleRepository, UserRole> {

	public UserRole findByName(UserRoleEnum roleUser) {
		return repository.findByName(roleUser).orElseThrow(() -> new AppException("User Role not set."));
	}

}
