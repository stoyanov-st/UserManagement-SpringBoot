package user.management.model;

import java.util.Collection;
import java.sql.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import lombok.Data;
import user.management.dto.UserDto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Data
public class User implements UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private Date dateOfBirth;
	private String phoneNumber;
	private String email;
	private boolean enabled;
	
	
	@ManyToMany
	@JoinTable( name = "users_roles",
				joinColumns = @JoinColumn(
						name = "user_id", referencedColumnName = "id"),
				inverseJoinColumns = @JoinColumn(
						name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;
	
	public User(){
		
	}

	public User(Long id, String username, String password, String firstName, String lastName, Date dateOfBirth, String phoneNumber, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
    public User(UserDto userDto, List<Role> roles){
        this(
                userDto.getId(),
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getDateOfBirth(),
                userDto.getPhoneNumber(),
                userDto.getEmail()
        );
        this.setRoles(roles);
    }

	@Override
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public void setRoles(final Collection<Role> roles) {
		this.roles = roles;
	}
	public Collection<Role> getRoles() {
		return roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean isAccountNonLocked() {
		
		return false;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	@Override
	public boolean isEnabled() {
		return enabled;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
}
