package user.management.dto;

import java.util.Collection;

import lombok.Data;
import lombok.EqualsAndHashCode;
import user.management.model.Role;
import user.management.model.User;

@EqualsAndHashCode(callSuper = true)
@Data
public class AdminDto extends UserDto{

	private boolean enabled;
	
	private Collection<Role> roles;
	
	public AdminDto(User user) {
		super(user);
		this.enabled = user.isEnabled();
		this.roles = user.getRoles();
	}

}
