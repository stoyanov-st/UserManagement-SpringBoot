package user.management.config;

import java.util.Arrays;
import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import user.management.model.Role;
import user.management.repository.RoleRepository;
import user.management.model.User;
import user.management.repository.UserRepository;

@Component
public class InitialDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    org.slf4j.Logger logger = LoggerFactory.getLogger(getClass().getName());
	private boolean alreadySetup = false;
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {

		if (alreadySetup){
		    logger.info("DB already setup");
		    return;
        }
		
		createRoleIfNotFound("ROLE_ADMIN");
		createRoleIfNotFound("ROLE_USER");
		
		final Role admin = roleRepository.findByName("ROLE_ADMIN");
		final User user = new User();
		user.setFirstName("John");
		user.setLastName("Doe");
		user.setUsername("admin");
		user.setPassword(passwordEncoder.encode("admin"));
		user.setRoles(Arrays.asList(admin));
		user.setEnabled(true);
		userRepository.save(user);
		
		alreadySetup = true;
		logger.info("DB setup");
	}

	@Transactional
	void createRoleIfNotFound(String roleName) {
		Role role = roleRepository.findByName(roleName);
		if (role == null) {
			role = new Role(roleName);
			roleRepository.save(role);
		}
	}
}
