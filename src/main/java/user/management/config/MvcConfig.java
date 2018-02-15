package user.management.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter{
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/register").setViewName("register");
		registry.addViewController("/users").setViewName("users");
		registry.addViewController("/admin").setViewName("admin");
		registry.addViewController("/search").setViewName("users");
		registry.addViewController("/admin/{id}").setViewName("admin-edit");
		registry.addViewController("/edit/{id}").setViewName("edit");
		registry.addViewController("/error").setViewName("error");
	}
}
