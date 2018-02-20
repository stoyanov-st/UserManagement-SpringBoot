package user.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;
import user.management.service.AdminService;

@Controller
public class AdminController {
	
	@Autowired
	private AdminService adminService;


	@RequestMapping(method=RequestMethod.GET, value="/admin")
	public ModelAndView getAllUsers() {
        return new ModelAndView().addObject("users", adminService.getAllUsers());
    }

	@RequestMapping(method=RequestMethod.GET, value="/admin/{id}")
	public ModelAndView editUser(@PathVariable long id) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("admin-edit");
	    modelAndView.addObject("user", adminService.getUserById(id));
        return  modelAndView;
	}

	@RequestMapping(method=RequestMethod.POST, value="/admin/filter")
	public ModelAndView sortUserList(@RequestBody String filter ) {
	    ModelAndView modelAndView = new ModelAndView();
	    modelAndView.setViewName("admin");
	    modelAndView.addObject("users", adminService.getAllUsers(filter));
	    modelAndView.addObject("filter", filter);
		return modelAndView;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/admin/search")
	public ModelAndView searchUsers(@RequestParam("search") String search) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
		modelAndView.addObject("users", adminService.searchUser(search));
		return modelAndView;
	}
}
