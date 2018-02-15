package user.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import user.management.model.Role;
import user.management.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUsername(String username);
	Page<User> findAllByRoles(Pageable pageRequest, Role role);
	Page<User> findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase(Pageable pageRequest, String firstName, String lastName, String email);
}
