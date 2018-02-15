package user.management.dto;

import java.util.Collection;

import user.management.model.Role;
import user.management.model.User;

public class AdminDto extends UserDto{

	private boolean enabled;
	
	private Collection<Role> roles;

	
	public AdminDto() {
		
	}
	
	public AdminDto(User user) {
		super(user);
		this.enabled = user.isEnabled();
		this.roles = user.getRoles();
	}
	
	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}
}
