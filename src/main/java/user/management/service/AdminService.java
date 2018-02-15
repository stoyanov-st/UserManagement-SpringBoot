package user.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import user.management.dto.AdminDto;
import user.management.model.User;
import user.management.dto.UserDto;
import user.management.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	public List<AdminDto> getAllUsers() {
		Page<User> users = adminRepository.findAll(new PageRequest(0, 10, Direction.ASC, "id"));
		
		return users.map(AdminDto::new).getContent();
	}

	public List<AdminDto> getAllUsers(String filter) {
		Page<User> users = adminRepository.findAll(new PageRequest(0, 10, Direction.ASC, filter));

		return users.map(AdminDto::new).getContent();
	}

	public AdminDto getUserById(long id) {
		User user = adminRepository.findOne(id);
		
		return new AdminDto(user);
	}
	
	public List<UserDto> searchUser(String search) {
		Page<User> users = adminRepository.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase(new PageRequest(0, 10, Direction.ASC, "id"), search, search, search);
		
		return users.map(UserDto::new).getContent();
	}
}
