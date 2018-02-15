package user.management.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import user.management.dto.UserDto;
import user.management.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String welcome() {
        return "home";
    }
	
	@RequestMapping(method=RequestMethod.POST, value="/registration")
	@ResponseStatus(HttpStatus.CREATED)
	public void registerUser (HttpServletResponse response, @Valid UserDto userDto, BindingResult bindingResult, Model model) throws IOException {
		if (bindingResult.hasErrors()) {
			response.sendRedirect("/register");
		}
		UserDto user = userService.registerUser(userDto);
		model.addAttribute("user", user);
		response.sendRedirect("users");
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users")
	public String getAllUsers(Model model) {
		List<UserDto> users = userService.getAllUsers();
		model.addAttribute("users", users);
		
	 	return "users";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/edit/{id}")
	public String editUser(Model model, @PathVariable long id) {
		UserDto user = userService.getUserById(id);
		model.addAttribute("user", user);
		
		return "edit";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/users/{userId}")
	public String updateUser(@PathVariable long userId, @Valid UserDto userDto) {
		userService.updateUser(userId, userDto);

		return "redirect:/users";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/users/{userId}")
	public String deleteUser(@PathVariable long userId) {
		userService.deleteUser(userId);

		return "redirect:/users";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/filter") 
	public String sortUserList(Model model, @RequestParam("filter") String filter ) {
		List<UserDto> users = userService.getAllUsers(filter);
		model.addAttribute("users", users);
		
		return "users";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public String searchUsers(Model model, @RequestParam("search") String search) {
		List<UserDto> users = userService.searchUser(search);
		model.addAttribute("users", users);

		return "users";
	}
}
