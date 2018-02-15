package user.management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import user.management.dto.AdminDto;
import user.management.dto.UserDto;
import user.management.service.AdminService;

@Controller
public class AdminController{
	
	@Autowired
	private AdminService adminService;


	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public String getAllUsers(Model model) {
		List<AdminDto> users = adminService.getAllUsers();
		model.addAttribute("users", users);
		
		return "admin";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/admin/{id}")
	public String editUser(Model model, @PathVariable long id) {
		UserDto user = adminService.getUserById(id);
		model.addAttribute("user", user);
		
		return "admin-edit";
	}

	@RequestMapping(method=RequestMethod.POST, value="/admin/filter")
	public String sortUserList(Model model, @RequestParam("filter") String filter ) {
		List<AdminDto> users = adminService.getAllUsers(filter);
		model.addAttribute("users", users);

		return "admin";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/admin/search")
	public String searchUsers(Model model, @RequestParam("search") String search) {
		List<UserDto> users = adminService.searchUser(search);
		model.addAttribute("users", users);

		return "admin";
	}
}
