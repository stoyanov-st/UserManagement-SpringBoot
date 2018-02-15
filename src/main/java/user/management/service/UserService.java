package user.management.service;

import java.util.Arrays;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import user.management.dto.UserDto;
import user.management.model.User;
import user.management.repository.RoleRepository;
import user.management.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired 
	private ModelMapper modelMapper;
	
	public UserDto registerUser(UserDto userDto) {
		User user = convertToUser(userDto);
		userRepository.save(user);
		
		return convertToDto(user);
	}

	private UserDto convertToDto(User user) {
		return modelMapper.map(user, UserDto.class);
	}
	
	private User convertToUser(UserDto userDto) {
		return new User(userDto, Arrays.asList(roleRepository.findByName("ROLE_USER")));
	}

	public UserDto getUserById(long userId) {
		return convertToDto(userRepository.findOne(userId));
	}
	
	public List<UserDto> getAllUsers() {
		Page<User> users = userRepository
				.findAllByRoles(new PageRequest(0, 10, Direction.ASC, "id"),
						roleRepository.findByName("ROLE_USER"));
		
		return users.map(UserDto::new).getContent();
	}
	
	public List<UserDto> getAllUsers(String filter) {
		Page<User> users = userRepository
				.findAllByRoles(new PageRequest(0, 10, Direction.ASC, filter),
						roleRepository.findByName("ROLE_USER"));
		
		return users.map(UserDto::new).getContent();
	}
	
	public void deleteUser(long userId) {
		userRepository.delete(userId);
	}

	public void updateUser(long userId, UserDto userDto) {
		User user = convertToUser(userDto);
		user.setId(userId);
		
		userRepository.save(user);
	}

	public List<UserDto> searchUser(String search) {
		Page<User> users = userRepository
				.findByFirstNameContainingOrLastNameContainingOrEmailContainingAllIgnoreCase(new PageRequest(0, 10, Direction.ASC, "id"),
						search,
						search,
						search);

		return users.map(UserDto::new).getContent();
	}
}
