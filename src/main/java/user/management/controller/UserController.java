package user.management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import user.management.dto.UserDto;
import user.management.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(method=RequestMethod.GET, value="/users")
	public ModelAndView getAllUsers() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("users");
		modelAndView.addObject("users", userService.getAllUsers());
	 	return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/edit/{id}")
	public ModelAndView editUser(@PathVariable long id) {
		ModelAndView modelAndView = new  ModelAndView();
		modelAndView.setViewName("edit");
		modelAndView.addObject("user", userService.getUserById(id));
		return modelAndView;
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
	public ModelAndView sortUserList(@RequestParam("filter") String filter ) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("users");
		modelAndView.addObject("users", userService.getAllUsers(filter));
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/search")
	public ModelAndView searchUsers(@RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("users");
		modelAndView.addObject("users", userService.searchUser(search));
		return modelAndView;
	}
}
