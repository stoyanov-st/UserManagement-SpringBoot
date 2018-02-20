package user.management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import user.management.dto.UserDto;
import user.management.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    UserService userService;

    @RequestMapping(value = {"/", "/home", "/index"}, method = RequestMethod.GET)
    public String welcome() {
        return "/home";
    }

    @RequestMapping(method=RequestMethod.POST, value="/registration")
    @ResponseStatus(HttpStatus.CREATED)
    public void registerUser (HttpServletResponse response, @Valid UserDto userDto, BindingResult bindingResult, Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getAllErrors());
            response.sendRedirect("/register");
        }
        else {
            UserDto user = userService.registerUser(userDto);
            model.addAttribute("user", user);
            response.sendRedirect("/users");
        }
    }

}
