package user.management.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import user.management.model.User;

public interface AdminRepository extends PagingAndSortingRepository<User, Long> {
	
	Page<User> findAll(Pageable pageRequest);
	Page<User> findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase(Pageable pageRequest, String firstName, String lastName, String email);
}
