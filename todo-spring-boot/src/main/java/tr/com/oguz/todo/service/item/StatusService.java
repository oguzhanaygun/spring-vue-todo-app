package tr.com.oguz.todo.service.item;

import org.springframework.stereotype.Service;

import tr.com.oguz.todo.persistence.entity.todo.Status;
import tr.com.oguz.todo.persistence.entity.todo.StatusName;
import tr.com.oguz.todo.persistence.repository.todo.StatusRepository;
import tr.com.oguz.todo.service.BaseService;

@Service
public class StatusService extends BaseService<StatusRepository, Status> {

	public Status findByName(StatusName name) {
		return repository.findByName(name);
	};

}
