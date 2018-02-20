package user.management.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler  {
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	 
    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        String targetUrl = determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

	private String determineTargetUrl(Authentication authentication) {
		String url = "";
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
        if (isAdmin(roles)) {
            logger.info("Redirect to /admin");
            url = "/admin";
        }
        if (isUser(roles)) {
            logger.info("Redirect to /user");
            url = "/users";
        }

        return url;
	}
    
    private boolean isUser(List<String> roles) {
	        return roles.contains("ROLE_USER");
	    }
	 
    private boolean isAdmin(List<String> roles) {
        return roles.contains("ROLE_ADMIN");
    }
    
}
