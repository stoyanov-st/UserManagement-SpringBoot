package user.management.repository;

import org.springframework.data.repository.CrudRepository;
import user.management.model.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {

	Role findByName(String name);

}
