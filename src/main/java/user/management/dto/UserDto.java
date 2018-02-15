package user.management.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import user.management.model.User;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UserDto {

	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@Size(min = 1)
	private String username;
	
	@NotNull
	@Size(min = 6)
	private String password;
	
	@NotNull
	@Size(min = 1)
	private String firstName;

	@NotNull
	@Size(min = 1)
	private String lastName;
	
	@NotNull
	private Date dateOfBirth;
	
	@NotNull
	@Size(min = 1)
	private String email;
	
	@NotNull
	@Size(min = 10, max = 10)
	private String phoneNumber;
	
	public UserDto(User user) {
		this.id = user.getId();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.phoneNumber = user.getPhoneNumber();
		this.email = user.getEmail();
		this.dateOfBirth = user.getDateOfBirth();
	}
}
